package io.ggogit.ggogit.api.tree;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchResponse;
import io.ggogit.ggogit.api.tree.dto.TreeInfoResponse;
import io.ggogit.ggogit.api.tree.dto.TreeTmpRequest;
import io.ggogit.ggogit.api.tree.dto.TreeTmpResponse;
import io.ggogit.ggogit.domain.book.service.BookService;
import io.ggogit.ggogit.domain.leaf.service.LeafService;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.MemberService;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import io.ggogit.ggogit.domain.tree.service.SeedService;
import io.ggogit.ggogit.domain.tree.service.TreeService;
import io.ggogit.ggogit.domain.tree.service.TreeTmpService;
import io.ggogit.ggogit.domain.tree.service.TreeUtilService;
import io.ggogit.ggogit.type.BookCategoryType;
import io.ggogit.ggogit.type.filterType;
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
import java.util.List;

@RestController
@RequestMapping("/trees")
@RequiredArgsConstructor
public class TreeController {

    private final TreeService treeService;
    private final TreeTmpService treeTmpService;
    private final BookService bookService;
    private final SeedService seedService;
    private final TreeUtilService treeUtilService;
    private final MemberService memberService;
    private final LeafService leafService;

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

//    @GetMapping("/book/reg")
//    public String getBookReg(
//            @RequestParam(value = "auto", required = false) boolean auto,
//            @RequestParam(value = "id", required = false) Long id,
//            HttpServletRequest request,
//            Model model
//    ) {
//        HttpSession session = request.getSession();
//
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        Long memberId = member.getId();
//
//        treeTmpService.deleteTmpById(memberId);
//
//        if (auto) {
//            BookInfoResponse book = bookService.getBookbyId(id);
//            model.addAttribute("book", book);
//            return "view/tree/book/reg-auto";
//        } else {
//            model.addAttribute("categories", BookCategoryType.values());
//            return "view/tree/book/reg";
//        }
//    }

    @PostMapping("/treeTmp/reg")
    public ResponseEntity<TreeTmpResponse> createBookTreeTmp(
            @RequestParam(required = false) MultipartFile img,
            @ModelAttribute TreeTmpRequest dto,
            @RequestParam(value = "auto") boolean auto,
            @SessionAttribute Member member,
            HttpServletRequest request
    ) throws IOException {

        TreeTmp treeTmp = dto.toTreeTmp(member);
        Long seedId = dto.getSeedId();
        Long bookCategoryId = dto.getBookCategoryId();

        Long treeTmpId  = treeTmpService.tmpTreeSave(treeTmp, member, seedId, bookCategoryId, img.getBytes(), img.getOriginalFilename());

        TreeTmpResponse resp = TreeTmpResponse.of(treeTmpId, "도서 트리 임시 저장 성공");

        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

//    @GetMapping("/etc/reg")
//    public String getTreeEtcReg(
//            @RequestParam(value = "type", required = false) String type,
//            HttpServletRequest request,
//            Model model
//    ) {
//        HttpSession session = request.getSession();
//
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        Long memberId= member.getId();
//
//        treeTmpService.deleteTmpById(memberId);
//
//        Seed seed = seedService.getByEngName(type);
//        model.addAttribute("seed", seed);
//        return "view/tree/reg-etc";
//    }

//    @PostMapping("/etc/reg")
//    public String postTreeEtcReg(
//            @RequestParam(value = "type", required = false) String type,
//            @RequestParam(required = false) MultipartFile img,
//            @ModelAttribute TreeTmpRequest tmp,
//            HttpServletRequest request
//    ) throws IOException  {
//
//
//
//        HttpSession session = request.getSession();
//
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        Long memberId= member.getId();
//
//        tmp.setMemberId(memberId);
//        Seed seed = seedService.getByEngName(type);
//        tmp.setSeedId(seed.getId());
//
//        if(img != null && !img.isEmpty()){
//            tmp.setImageFile(treeUtilService.updateImageFile(img,request.getSession().getServletContext().getRealPath("/image/tmp")));
//        }
//
//        treeTmpService.tmpTreeSave(TreeTmpRequest.toEtcTreeTmp(tmp,member,seed));
//        return "redirect:/leaf/first/reg?seed=" + seed.getEngName();
//    }


    @GetMapping("/{treeId}/leafs")
    public Page<LeafBranchResponse> getBranchList(
            @PathVariable Long treeId,
            @RequestParam(value = "bookMark", required = false) final Boolean bookMark,
            @RequestParam(value = "filter", required = false) final  Long filter,
            @RequestParam(value = "sort", required = false) final  Long sort,
            @RequestParam(value = "p", defaultValue = "1") final int page,
            @SessionAttribute Member member,
            HttpServletRequest request
            ) {
        int size = 10;

        filterType filterName = filterType.fromNumber(filter);
        filterType sortName = filterType.fromNumber(sort);
        Sort s = filterType.createSort(filterName,sortName);

        Pageable pageable = PageRequest.of(page, size, s);

        Boolean hasOwner = treeService.isOwner(treeId, member.getId());

        List<LeafBranchResponse> leafList = leafService.findBranchByFilter(treeId, hasOwner, bookMark);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), leafList.size());
        Page<LeafBranchResponse> leafCard = new PageImpl<>(leafList.subList(start, end), pageable, leafList.size());

        return leafCard;

    }


//    @GetMapping("/book/select")
//    public String searchBook(@ModelAttribute(name = "target") String target, Model model)
//    {
//
//        //검색어가 없을때, 최초 페이지 진입시
//        if(target.isEmpty()){
//            model.addAttribute("bookPreviews", new BookPreviewView());
//            model.addAttribute("resultCnt", 0);
//            model.addAttribute("target", "");
//            model.addAttribute("searchTypes", bookSearchType.createBookSearchTypes());
//
//            return "view/tree/book/select";
//        }
//
//        //검색어가 있을 때
//        List<BookPreviewView> books = (List<BookPreviewView>)model.getAttribute("bookPreviews");
//        int cnt = 0;
//        if(books!=null){
//            cnt = books.size();
//        }
//        model.addAttribute("resultCnt", cnt);
//        model.addAttribute("target", target);
//        model.addAttribute("searchTypes", model.getAttribute("searchTypes"));
//        return "view/tree/book/select";
//    }
//
//    //Type=  검색 기준
//    // t: title 검색
//    // a: author 검색
//    @PostMapping("/book/select")
//    public String PostSearchBook(@RequestParam(name = "target", defaultValue = "") String target
//            ,@RequestParam(name = "type", defaultValue = "t") String type
//            ,RedirectAttributes redirectAttributes, Model model){
//
//        //빈 값이면 아무것도 하지 않고 바로 리다이렉트 하자.
//        if(target.isBlank()){
//            return "redirect:/tree/book/select";
//        }
//
//        List<BookPreviewView> books = new ArrayList<>();
//        List<bookSearchType> bookSearchTypes = bookSearchType.createBookSearchTypes();
//        bookSearchTypes.stream().forEach(
//                value -> {
//                    if(value.getValue().equals(type)) value.setIsChecked("checked");
//                    else {value.setIsChecked(null);}
//                });
//        switch (type) {
//            case "t": {
//                //get메서드가 받는 모델에 자동으로 포함됨
//                books = bookService.getBooksbyTitle(target);
//                break;
//            }
//            case "a": {
//                //get메서드가 받는 모델에 자동으로 포함됨
//                books = bookService.getBooksbyAuthor(target);
//                break;
//            }
//        }
//
//        redirectAttributes.addFlashAttribute("bookPreviews", books);
//        redirectAttributes.addFlashAttribute("target", target);
//        redirectAttributes.addFlashAttribute("searchTypes", bookSearchTypes);
//
//        return "redirect:/tree/book/select";
//    }

//    @GetMapping("/index/{treeId}")
//    public String getTreeIndex(
//            @PathVariable(name = "treeId") Long treeId,
//            @SessionAttribute Member member
//    ) {
//        CombineTreeView combineTreeView = treeService.findCombineTreeView(member.getId(), treeId);
//        MemberImageView memberImageView = combineTreeView.getMemberImageView();
//        TreeInfoResponse treeInfoResponse = combineTreeView.getTreeInfoResponse();
//
//        List<LeafBranchView> leafList = leafService.findBranchByTreeId(treeId);
//
//        return ;
//    }
//
//    @GetMapping("/memoir/register/index")
//    public String getmemoirindex(Model model) {
//        model.addAttribute("categories", BookCategoryType.values());
//        return "view/memoir/index";
//    }
}