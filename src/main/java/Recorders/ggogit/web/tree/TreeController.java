package Recorders.ggogit.web.tree;

import Recorders.ggogit.domain.book.service.BookService;
import Recorders.ggogit.domain.book.view.BookInfoView;
import Recorders.ggogit.domain.book.view.BookPreviewView;
import Recorders.ggogit.domain.leaf.view.LeafBranchView;
import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.service.MemberService;
import Recorders.ggogit.domain.member.view.MemberImageView;
import Recorders.ggogit.domain.tree.entity.Seed;
import Recorders.ggogit.domain.tree.service.SeedService;
import Recorders.ggogit.domain.tree.service.TreeServiceImpl;
import Recorders.ggogit.domain.tree.view.CombineTreeView;
import Recorders.ggogit.domain.tree.view.TreeInfoView;
import Recorders.ggogit.web.book.form.bookSearchType;
import Recorders.ggogit.type.BookCategoryType;
import Recorders.ggogit.web.member.session.SessionConst;
import Recorders.ggogit.web.tree.form.TreeEtcSaveTmpForm;
import Recorders.ggogit.web.tree.form.TreeSaveTmpForm;
import Recorders.ggogit.domain.tree.util.TreeUtilService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tree")
@RequiredArgsConstructor
public class TreeController {

    @Autowired
    private TreeServiceImpl treeService;

    @Autowired
    private BookService bookService;

    @Autowired
    private SeedService seedService;

    @Autowired
    private TreeUtilService treeUtilService;

    @Autowired
    private MemberService memberService;

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

        Long memberId= member.getId();
//        Member member = (Member) request
//                .getSession().getAttribute(SessionConst.LOGIN_MEMBER);

        treeService.deleteTmpFormById(memberId);

        if (auto) {
            BookInfoView book = bookService.getBookbyId(id);
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
            @ModelAttribute TreeSaveTmpForm form,
            @RequestParam(value = "auto") boolean auto,
            HttpServletRequest request
    ) throws IOException {

        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Long memberId= member.getId();

        form.setMemberId(memberId);
        form.setSeedId(1L);

        System.out.println(form.toString());

        if(!auto && img != null && !img.isEmpty()){
            //이미지 저장 서비스 : path 경로에 img 저장 후 fullpath 경로 String 리턴
            form.setImageFile(treeUtilService.updateImageFile(img,request.getSession().getServletContext().getRealPath("/image/tmp")));
        }

        treeService.tmpTreeSave(form);
        Seed seed = seedService.get(form.getSeedId());

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

        treeService.deleteTmpFormById(memberId);

        Seed seed = seedService.getByEngName(type);
        model.addAttribute("seed", seed);
        return "view/tree/reg-etc";
    }

    @PostMapping("/etc/reg")
    public String postTreeEtcReg(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(required = false) MultipartFile img,
            @ModelAttribute TreeEtcSaveTmpForm form,
            HttpServletRequest request
    ) throws IOException  {



        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Long memberId= member.getId();

        form.setMemberId(memberId);
        Seed seed = seedService.getByEngName(type);
        form.setSeedId(seed.getId());

        if(img != null && !img.isEmpty()){
            form.setImageFile(treeUtilService.updateImageFile(img,request.getSession().getServletContext().getRealPath("/image/tmp")));
        }

        treeService.tmpEtcTreeSave(form);
        return "redirect:/leaf/first/reg?seed=" + seed.getEngName();
    }


    @GetMapping("/list")
    public String getBranchList(Model model) {
        return "view/tree/list";
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

    @RequestMapping("/detail/{treeId}")
    public String getTreeDetail(
            Model model,
            @PathVariable(name = "treeId") Long treeId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId= member.getId();

        CombineTreeView combineTreeView = treeService.setCombineTreeView(memberId,treeId);
        MemberImageView memberImageView = combineTreeView.getMemberImageView();
        TreeInfoView treeInfoView = combineTreeView.getTreeInfoView();
        List<LeafBranchView> leafList = combineTreeView.getLeafList();
        System.out.println(leafList.toString());

        model.addAttribute("memberImageView", memberImageView);
        model.addAttribute("treeInfoView", treeInfoView);
        model.addAttribute("leafList", leafList);
        return "view/tree/index";
    }

    @GetMapping("/memoir/register/index")
    public String getmemoirindex(Model model) {
        model.addAttribute("categories", BookCategoryType.values());
        return "view/memoir/index";
    }
}