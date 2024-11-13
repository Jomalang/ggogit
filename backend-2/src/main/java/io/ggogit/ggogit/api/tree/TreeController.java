package io.ggogit.ggogit.api.tree;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchResponse;
import io.ggogit.ggogit.api.tree.dto.*;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.service.LeafDtoService;
import io.ggogit.ggogit.domain.member.security.CustomUserDetails;
import io.ggogit.ggogit.domain.member.service.MemberService;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.service.SeedService;
import io.ggogit.ggogit.domain.tree.service.TreeService;
import io.ggogit.ggogit.domain.tree.service.TreeTmpService;
import io.ggogit.ggogit.type.FilterType;
import io.ggogit.ggogit.util.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trees")
@RequiredArgsConstructor
public class TreeController {

    private final TreeService treeService;
    private final TreeTmpService treeTmpService;
    private final LeafDtoService leafDtoService;
    private final SeedService seedService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;


    @GetMapping("/search")
    public Page<TreeSearchResultResponse> treeSearch(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @ModelAttribute TreeSearchQuery query
    ) {
        Long memberId = userDetails.getId();

        Page<Tree> trees = treeService.findTreeByQueryAndMemberId(query, memberId);

        // Tree 객체들을 TreeSearchResultResponse로 변환
        List<TreeSearchResultResponse> responseList = trees.stream()
                .map(tree -> {
                    if ("도서".equals(tree.getSeed().getKorName())) {
                        return TreeSearchResultResponse.ofBook(tree); // '도서'일 때의 변환
                    } else {
                        return TreeSearchResultResponse.ofEtc(tree); // '도서'가 아닐 때의 변환
                    }
                })
                .collect(Collectors.toList());

        // 새로운 Page 객체 생성
        return new PageImpl<>(responseList, trees.getPageable(), trees.getTotalElements());
    }

    @GetMapping("/search/result/{treeSearchText}")
    public String treeSearchResult(
            @PathVariable String treeSearchText,
            Model model
    ) {
        return "view/tree/search/tree-list";
    }

    @PostMapping("/search/result/{treeSearchText}")
    public String treeSearchResult(
            @RequestParam("treeSearchText") String treeSearchText,
            RedirectAttributes redirectAttributes
    ) {
        System.out.println(treeSearchText);
        redirectAttributes.addAttribute("treeSearchText", treeSearchText);
        return "redirect:/tree/search/result/{treeSearchText}";
    }

    @GetMapping("{id}/info")
    public ResponseEntity<TreeInfoResponse> getTreeInfoResponse(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable(value = "id") Long treeId
    ) {
        Long memberId = userDetails.getId();
        TreeInfoResponse treeInfoResponse = treeService.findTreeInfoResponse(memberId, treeId);
        return new ResponseEntity<> (treeInfoResponse, HttpStatus.OK);
    }

    /**
     * 리프 아이디로 트리 정보 조회
     * */
    @GetMapping("leaves/{leafId}/info")
    public ResponseEntity<TreeInfoResponse> getTreeInfoResponseByLeafId(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long leafId
    ) {
        Long memberId = userDetails.getId();
        Tree tree = leafDtoService.getTree(leafId);
        TreeInfoResponse treeInfoResponse = treeService.findTreeInfoResponse(memberId, tree.getId());
        return new ResponseEntity<> (treeInfoResponse, HttpStatus.OK);
    }


//    @GetMapping("{id}/info")
//    public Page<TreeInfoResponse> getTreeInfoResponseList(
//            @RequestParam(value = "s", required = false) Long seedId,
//            @RequestParam(value = "p", defaultValue = "0") int page,
//            @RequestParam(value = "mid",defaultValue = "1") Long mid
////            @SessionAttribute Member member
//    ) {
////        Long memberId = member.getId();
//        Long memberId = mid;
//        System.out.println("memberId = " + memberId);
//        System.out.println("seedId = " + seedId);
//
//        int size = 10;
//        Sort s = Sort.by(Sort.Order.desc("updateTime"));
//        Pageable pageable = PageRequest.of(page, size, s);
//        Page<TreeInfoResponse> list = treeService.findTreeInfoResponseList(memberId, pageable);
//        return list;
//    }
//    @GetMapping
//    public Page<TreeCardRequest> getTreeList(
//            @RequestParam(value = "s", required = false) Long seedId,
//            @RequestParam(value = "p", defaultValue = "0") int page,
//            @RequestParam(value = "mid",defaultValue = "1") Long mid
//    ) {
//        Long memberId = member.getId();
//        Long memberId = mid;
//        System.out.println("memberId = " + memberId);
//        System.out.println("seedId = " + seedId);
//
//        int size = 10;
//        Sort s = Sort.by(Sort.Order.desc("updateTime"));
//        Pageable pageable = PageRequest.of(page, size, s);
//        Page<TreeCardRequest> list = treeService.findTreeCardRequestList(seedId,memberId, pageable);
//        return list;
//    }

    @GetMapping("/{treeId}/branches")
    public ResponseEntity<TreeDetailResponse> getBranchList(
            @PathVariable Long treeId,
            @Valid @ModelAttribute TreeBranchFilter filter
    ) {

        int size = 10;
        int page = filter.getPage();
        Boolean bookMark = filter.getBookMark();

        FilterType filterName = FilterType.fromNumber(filter.getFilter());
        FilterType sortName = FilterType.fromNumber(filter.getSort());
        System.out.println("filter = " + filterName);
        System.out.println("sort = " + sortName);
        System.out.println("--------------------------------------------------------------------------------------");
        Sort s = FilterType.createSort(filterName, sortName);

//        Boolean hasOwner = treeService.isOwner(treeId, member.getId());
        Boolean hasOwner = true;//테스트용 코드
        int totalPage = 0;

        List<LeafBranchResponse> branchList = leafDtoService.findBranchByFilter(treeId, hasOwner, bookMark);
        totalPage = branchList.size();

        branchList = sortLeafList(branchList, filterName.getValue(), sortName.getValue());

        if(page >= 0) {
            branchList = branchList.stream()
                    .skip((long) page * size)
                    .limit(size)
                    .collect(Collectors.toList());
        }
        TreeDetailResponse response = TreeDetailResponse.of(branchList, totalPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{treeId}/leafs")
    public Page<Leaf> getLeafList(
            @PathVariable Long treeId,
            @RequestParam(value = "p", defaultValue = "0") final int page
//            @SessionAttribute Member member
    ) {


        int size = 10;
        Sort s = Sort.by(Sort.Order.desc("updateTime"));
        Pageable pageable = PageRequest.of(page, size, s);

//        Boolean hasOwner = treeService.isOwner(treeId, member.getId());
        Boolean hasOwner = true;//테스트용 코드

        Page<Leaf> leafList = leafDtoService.findLeafByTreeId(treeId, hasOwner, pageable);

        return leafList;
    }

    private List<LeafBranchResponse> sortLeafList(List<LeafBranchResponse> leafList, String filterName, String sortName) {
        Comparator<LeafBranchResponse> comparator;
        switch (filterName) {
            case "TITLE":
                comparator = Comparator.comparing(LeafBranchResponse::getTitle);
                break;
            case "VIEW_COUNT":
                comparator = Comparator.comparing(LeafBranchResponse::getViewCount);
                break;
            case "LIKE_COUNT":
                comparator = Comparator.comparing(LeafBranchResponse::getLikeCount);
                break;
            case "LEAF_CNT":
                comparator = Comparator.comparing(LeafBranchResponse::getLeafCount);
                break;
            default:
                comparator = Comparator.comparing(LeafBranchResponse::getUpdateTime);
                break;
        }

        if (sortName.equals("DESC")) {
            comparator = comparator.reversed();
        }

        return leafList.stream().sorted(comparator).collect(Collectors.toList());
    }

    //Access Token을 받아서 TreeInfoResponseHome을 반환하는 API
//    @GetMapping("tree-home")
//    public ResponseEntity<TreeInfoResponseHome> getTreeInfoResponses(
//       @RequestHeader(value="Authorization") String accessToken) {
//
//        Long memberId = jwtTokenProvider.getMemberIdFromToken(accessToken);
//        List<TreeInfoResponse> treeInfoResponseList = treeService.findTreeInfoResponseList(memberId);
//
//        return new ResponseEntity<>(TreeInfoResponseHome.of(treeInfoResponseList), HttpStatus.OK);
//    }
    @GetMapping("tree-home")
    public ResponseEntity<TreeInfoResponseHome> getTreeInfoResponses(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long memberId = userDetails.getId();
        List<TreeInfoResponse> treeInfoResponseList = treeService.findTreeInfoResponseList(memberId);

        return new ResponseEntity<>(TreeInfoResponseHome.of(treeInfoResponseList), HttpStatus.OK);
    }

    @GetMapping("tree-home-sort")
    public ResponseEntity<TreeInfoResponseHome> getTreeInfoResponsesSort(
            @RequestParam(value = "seedId", required = false) Long seedId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long memberId = userDetails.getId();
        List<TreeInfoResponse> treeInfoResponseList = treeService.findTreeInfoResponseList(memberId, seedId);

        return new ResponseEntity<>(TreeInfoResponseHome.of(treeInfoResponseList), HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}/trees/book/cards")
    public ResponseEntity<TreeBookCardResponseList> getTreeBookCardResponse(
            @PathVariable(value="memberId", required = true) Long memberId
    ) {
        int offset = 0;
        int limit = 10;
        Sort sort = Sort.by(Sort.Order.desc("updateTime"));
        Pageable pageable = PageRequest.of(offset, limit, sort);

        List<Tree> allByMemberId = treeService.findAllPages(memberId, pageable).getContent();
        List<TreeBookCardResponse> treeBookCardRespons = allByMemberId.stream()
                .map(tree -> TreeBookCardResponse.toEntity(tree.getBook(), true, tree, tree.getSeed(), memberId))
                .toList();

        return new ResponseEntity<>(TreeBookCardResponseList.of(treeBookCardRespons), HttpStatus.OK);
    }

    @GetMapping("members/{memberId}/books/{bookId}/trees/cards")
    public ResponseEntity<TreeBookCardResponseList> getBookTreeResponse(
            @PathVariable(name="memberId", required = true) Long memberId,
            @PathVariable(name="bookId", required = true) Long bookId){

        List<Tree> allByBookId = treeService.findAllByBookId(memberId, bookId).getContent();
        System.out.println("size = " + String.valueOf(allByBookId.size()));
        //해당 책에 멤버가 소유한 트리가 없을 경우
        if(allByBookId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<TreeBookCardResponse> treeBookCardRespons = allByBookId.stream()
                .map(tree -> TreeBookCardResponse.toEntity(tree.getBook(), true, tree, tree.getSeed(), memberId))
                .toList();

        return new ResponseEntity<>(TreeBookCardResponseList.of(treeBookCardRespons), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}/trees/cards")
    public ResponseEntity<TreeCardDtoResponse> getTreeCardDtoResponse(
            @PathVariable(name="bookId") Long bookId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Tree> trees = treeService.findAllCardByBookId(bookId, page, size);
        return new ResponseEntity<>(TreeCardDtoResponse.of(trees), HttpStatus.OK);
    }

}
