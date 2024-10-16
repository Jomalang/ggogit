package io.ggogit.ggogit.api.tree;

import io.ggogit.ggogit.api.book.dto.BookInfoResponse;
import io.ggogit.ggogit.api.member.session.SessionConst;
import io.ggogit.ggogit.api.tree.dto.TreeEtcTmpRequest;
import io.ggogit.ggogit.api.tree.dto.TreeInfoResponse;
import io.ggogit.ggogit.api.tree.dto.TreeTmpRequest;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.book.service.BookService;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.service.LeafService;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.MemberService;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.service.SeedService;
import io.ggogit.ggogit.domain.tree.service.TreeService;
import io.ggogit.ggogit.domain.tree.service.TreeTmpService;
import io.ggogit.ggogit.domain.tree.service.TreeUtilService;
import io.ggogit.ggogit.type.BookCategoryType;
import io.ggogit.ggogit.type.filterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
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

    @GetMapping("/book/reg")
    public String getBookReg(
            @RequestParam(value = "auto", required = false) boolean auto,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model
    ) {
        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Long memberId = member.getId();

        treeTmpService.deleteTmpById(memberId);

        if (auto) {
            BookInfoResponse book = bookService.getBookbyId(id);
            model.addAttribute("book", book);
            return "view/tree/book/reg-auto";
        } else {
            model.addAttribute("categories", BookCategoryType.values());
            return "view/tree/book/reg";
        }
    }

    @PostMapping("/book/reg")
    public String postBookReg(
            @RequestParam(required = false) MultipartFile img,
            @ModelAttribute TreeTmpRequest tmp,
            @RequestParam(value = "auto") boolean auto,
            HttpServletRequest request
    ) throws IOException {

        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Long memberId= member.getId();

        BookCategory bookCategory = bookService.getBookCategory(tmp.getBookCategoryId());


        tmp.setMemberId(memberId);
        Seed seed = seedService.get(1L);


        if(!auto && img != null && !img.isEmpty()){
            //이미지 저장 서비스 : path 경로에 img 저장 후 fullpath 경로 String 리턴
            tmp.setImageFile(treeUtilService.updateImageFile(img,request.getSession().getServletContext().getRealPath("/image/tmp")));
        }

        treeTmpService.tmpTreeSave(TreeTmpRequest.toBookTreeTmp(tmp,member,bookCategory,seed));

        return "redirect:/leaf/first/reg?seed=" + seed.getEngName();
    }

    @GetMapping("/etc/reg")
    public String getTreeEtcReg(
            @RequestParam(value = "type", required = false) String type,
            HttpServletRequest request,
            Model model
    ) {
        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Long memberId= member.getId();

        treeTmpService.deleteTmpById(memberId);

        Seed seed = seedService.getByEngName(type);
        model.addAttribute("seed", seed);
        return "view/tree/reg-etc";
    }

    @PostMapping("/etc/reg")
    public String postTreeEtcReg(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(required = false) MultipartFile img,
            @ModelAttribute TreeTmpRequest tmp,
            HttpServletRequest request
    ) throws IOException  {



        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Long memberId= member.getId();

        tmp.setMemberId(memberId);
        Seed seed = seedService.getByEngName(type);
        tmp.setSeedId(seed.getId());

        if(img != null && !img.isEmpty()){
            tmp.setImageFile(treeUtilService.updateImageFile(img,request.getSession().getServletContext().getRealPath("/image/tmp")));
        }

        treeTmpService.tmpTreeSave(TreeTmpRequest.toEtcTreeTmp(tmp,member,seed));
        return "redirect:/leaf/first/reg?seed=" + seed.getEngName();
    }


    @GetMapping("/{treeId}/leafs")
    public List<Tree> getBranchList(
            @PathVariable Long treeId,,
            @RequestParam(value = "bookMark", required = false) final Boolean bookMark,
            @RequestParam(value = "filter", required = false) final  Long filter,
            @RequestParam(value = "sort", required = false) final  Long sort,
            @RequestParam(value = "p", defaultValue = "1") final int page,
            HttpServletRequest request
            ) {
        int size = 10;


        filterType filterName = filterType.fromNumber(filter);
        filterType sortName = filterType.fromNumber(sort);
        Sort s = filterType.createSort(filterName,sortName);

        Pageable pageable = PageRequest.of(page, size, s);

        Boolean hasOwner = treeService.isOwner(treeId, request)

        Page<Leaf> pageList = leafService.findBranchByFilter(treeId, hasOwner, bookMark, pageable);
    }


    @GetMapping("/book/select")
    public String searchBook(@ModelAttribute(name = "target") String target, Model model)
    {

        //검색어가 없을때, 최초 페이지 진입시
        if(target.isEmpty()){
            model.addAttribute("bookPreviews", new BookPreviewView());
            model.addAttribute("resultCnt", 0);
            model.addAttribute("target", "");
            model.addAttribute("searchTypes", bookSearchType.createBookSearchTypes());

            return "view/tree/book/select";
        }

        //검색어가 있을 때
        List<BookPreviewView> books = (List<BookPreviewView>)model.getAttribute("bookPreviews");
        int cnt = 0;
        if(books!=null){
            cnt = books.size();
        }
        model.addAttribute("resultCnt", cnt);
        model.addAttribute("target", target);
        model.addAttribute("searchTypes", model.getAttribute("searchTypes"));
        return "view/tree/book/select";
    }

    //Type=  검색 기준
    // t: title 검색
    // a: author 검색
    @PostMapping("/book/select")
    public String PostSearchBook(@RequestParam(name = "target", defaultValue = "") String target
            ,@RequestParam(name = "type", defaultValue = "t") String type
            ,RedirectAttributes redirectAttributes, Model model){

        //빈 값이면 아무것도 하지 않고 바로 리다이렉트 하자.
        if(target.isBlank()){
            return "redirect:/tree/book/select";
        }

        List<BookPreviewView> books = new ArrayList<>();
        List<bookSearchType> bookSearchTypes = bookSearchType.createBookSearchTypes();
        bookSearchTypes.stream().forEach(
                value -> {
                    if(value.getValue().equals(type)) value.setIsChecked("checked");
                    else {value.setIsChecked(null);}
                });
        switch (type) {
            case "t": {
                //get메서드가 받는 모델에 자동으로 포함됨
                books = bookService.getBooksbyTitle(target);
                break;
            }
            case "a": {
                //get메서드가 받는 모델에 자동으로 포함됨
                books = bookService.getBooksbyAuthor(target);
                break;
            }
        }

        redirectAttributes.addFlashAttribute("bookPreviews", books);
        redirectAttributes.addFlashAttribute("target", target);
        redirectAttributes.addFlashAttribute("searchTypes", bookSearchTypes);

        return "redirect:/tree/book/select";
    }

    @RequestMapping("/index/{treeId}")
    public String getTreeDetail(
            Model model,
            @PathVariable(name = "treeId") Long treeId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId= member.getId();

        CombineTreeView combineTreeView = treeService.findCombineTreeView(memberId, treeId);
        MemberImageView memberImageView = combineTreeView.getMemberImageView();
        TreeInfoResponse treeInfoResponse = combineTreeView.getTreeInfoResponse();

        List<LeafBranchView> leafList = leafService.findBranchByTreeId(treeId);

        return ;
    }

    @GetMapping("/memoir/register/index")
    public String getmemoirindex(Model model) {
        model.addAttribute("categories", BookCategoryType.values());
        return "view/memoir/index";
    }
}