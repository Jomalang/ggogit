package io.ggogit.ggogit.api.tree;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchResponse;
import io.ggogit.ggogit.api.tree.dto.TreeCardRequest;
import io.ggogit.ggogit.api.tree.dto.TreeTmpRequest;
import io.ggogit.ggogit.api.tree.dto.TreeTmpResponse;

import io.ggogit.ggogit.domain.leaf.service.LeafDtoService;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import io.ggogit.ggogit.domain.tree.service.SeedService;
import io.ggogit.ggogit.domain.tree.service.TreeService;
import io.ggogit.ggogit.domain.tree.service.TreeTmpService;
import io.ggogit.ggogit.type.FilterType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

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
    @GetMapping()
    public Page<TreeCardRequest> getTreeList(
            @RequestParam(value = "s", required = false) Long seedId,
            @RequestParam(value = "p", defaultValue = "0") int page,
            @RequestParam(value = "mid",defaultValue = "1") Long mid
//            @SessionAttribute Member member
    ) {
//        Long memberId = member.getId();
        Long memberId = mid;
        System.out.println("memberId = " + memberId);
        System.out.println("seedId = " + seedId);

        int size = 10;
        Sort s = Sort.by(Sort.Order.desc("updateTime"));
        Pageable pageable = PageRequest.of(page, size, s);
        Page<TreeCardRequest> list = treeService.findTreeCardRequestList(seedId,memberId, pageable);
        return list;
    }

    @PostMapping("/treeTmp/reg")
    public ResponseEntity<TreeTmpResponse> createBookTreeTmp (
            @RequestParam(required = false) MultipartFile img,
            @ModelAttribute TreeTmpRequest dto,
            @RequestParam(value = "auto") boolean auto,
            @SessionAttribute Member member,
            HttpServletRequest request
    ) throws IOException {

        TreeTmp treeTmp = dto.toTreeTmp(member);
        Long seedId = dto.getSeedId();
        Long bookCategoryId = dto.getBookCategoryId();

        Long treeTmpId = treeTmpService.tmpTreeSave(treeTmp, member, seedId, bookCategoryId, img.getBytes(), img.getOriginalFilename());

        TreeTmpResponse resp = TreeTmpResponse.of(treeTmpId, "도서 트리 임시 저장 성공");

        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @GetMapping("/{treeId}/leafs")
    public Page<LeafBranchResponse> getBranchList(
            @PathVariable Long treeId,
            @RequestParam(value = "bookMark", required = false) final Boolean bookMark,
            @RequestParam(value = "filter", defaultValue = "10") final Long filter,
            @RequestParam(value = "sort", defaultValue = "1") final Long sort,
            @RequestParam(value = "p", defaultValue = "0") final int page
//            @SessionAttribute Member member
            ) {

        int size = 10;
        FilterType filterName = FilterType.fromNumber(filter);
        FilterType sortName = FilterType.fromNumber(sort);
        Sort s = FilterType.createSort(filterName, sortName);
        Pageable pageable = PageRequest.of(page, size, s);

//        Boolean hasOwner = treeService.isOwner(treeId, member.getId());
        Boolean hasOwner = false;//테스트용 코드

        List<LeafBranchResponse> leafList = leafDtoService.findBranchByFilter(treeId, hasOwner, bookMark);
        leafList = sortLeafList(leafList, filterName.getValue(), sortName.getValue());
        for (LeafBranchResponse leaf : leafList) {
            System.out.println(leaf.toString());
        }
        if (leafList.isEmpty()) {
            return Page.empty(pageable);
        }

        int start = (int) pageable.getOffset();
        if (start >= leafList.size()) {
            return Page.empty(pageable);
        }

        int end = Math.min(start + pageable.getPageSize(), leafList.size());

        try {
            Page<LeafBranchResponse> leafCard = new PageImpl<>(
                    leafList.subList(start, end), pageable, leafList.size());
            return leafCard;
        } catch (IllegalArgumentException e) {
            System.out.println("Paging error: " + e.getMessage());
            return Page.empty(pageable);
        }
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
}
