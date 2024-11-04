package io.ggogit.ggogit.api.tree;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchResponse;
import io.ggogit.ggogit.api.tree.dto.*;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.service.LeafDtoService;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.MemberService;
import io.ggogit.ggogit.domain.member.service.MemberServiceImpl;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import io.ggogit.ggogit.domain.tree.service.SeedService;
import io.ggogit.ggogit.domain.tree.service.TreeService;
import io.ggogit.ggogit.domain.tree.service.TreeTmpService;
import io.ggogit.ggogit.type.FilterType;
import io.ggogit.ggogit.util.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.Response;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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
    public String treeSearch() {
        return "view/tree/search/index";
    }

    @PostMapping("/search")
    public String treeSearch(
            @RequestParam("treeSearchText") String treeSearchText,
            RedirectAttributes redirectAttributes
    ) {
        System.out.println(treeSearchText);
        redirectAttributes.addAttribute("treeSearchText", treeSearchText);
        return "redirect:/tree/search/result/{treeSearchText}";
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
            @PathVariable(value = "id") Long treeId,
            @RequestParam(value = "mid",defaultValue = "1") Long mid
//            @SessionAttribute Member member
    ) {
//        Long memberId = member.getId();
        Long memberId = mid;

        TreeInfoResponse treeInfoResponse = treeService.findTreeInfoResponse(memberId, treeId);
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

        List<LeafBranchResponse> branchList = leafDtoService.findBranchByFilter(treeId, hasOwner, bookMark);
        branchList = sortLeafList(branchList, filterName.getValue(), sortName.getValue());

        if(page >= 0) {
            branchList = branchList.stream()
                    .skip((long) page * size)
                    .limit(size)
                    .collect(Collectors.toList());
        }
        TreeDetailResponse response = TreeDetailResponse.of(branchList, branchList.size());
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
            @RequestParam(value = "mid",defaultValue = "1") Long mid) {

        Long memberId = mid;
        List<TreeInfoResponse> treeInfoResponseList = treeService.findTreeInfoResponseList(memberId);

        return new ResponseEntity<>(TreeInfoResponseHome.of(treeInfoResponseList), HttpStatus.OK);
    }

    @GetMapping("tree-home-sort")
    public ResponseEntity<TreeInfoResponseHome> getTreeInfoResponsesSort(
            @RequestParam(value = "mid",defaultValue = "1") Long mid,
            @RequestParam(value = "seedId", required = false) Long seedId
    ) {
        Long memberId = mid;
        List<TreeInfoResponse> treeInfoResponseList = treeService.findTreeInfoResponseList(memberId, seedId);

        return new ResponseEntity<>(TreeInfoResponseHome.of(treeInfoResponseList), HttpStatus.OK);
    }
}
