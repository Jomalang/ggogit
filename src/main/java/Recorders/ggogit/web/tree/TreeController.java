package Recorders.ggogit.web.tree;

import Recorders.ggogit.type.BookCategoryType;
import Recorders.ggogit.type.SeedCategoryType;
import Recorders.ggogit.domain.book.service.BookService;
import Recorders.ggogit.domain.book.view.BookPreviewView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller()
@RequestMapping("/tree")
@RequiredArgsConstructor
public class TreeController {

    private final BookService bookService;

    @GetMapping("/search")
    public String treeSearch() {
        return "view/tree/search/index";
    }

    @PostMapping("/search")
    public String treeSearch(@RequestParam("treeSearchText") String treeSearchText,
                             RedirectAttributes redirectAttributes) {
        System.out.println(treeSearchText);
        redirectAttributes.addAttribute("treeSearchText", treeSearchText);
        return "redirect:/tree/search/result/{treeSearchText}";
    }

    @GetMapping("/search/result/{treeSearchText}")
    public String treeSearchResult(@PathVariable String treeSearchText,
                                   Model model) {
        return "view/tree/search/tree-list";
    }

    @PostMapping("/search/result/{treeSearchText}")
    public String treeSearchResult(@RequestParam("treeSearchText") String treeSearchText,
                                   RedirectAttributes redirectAttributes) {
        System.out.println(treeSearchText);
        redirectAttributes.addAttribute("treeSearchText", treeSearchText);
        return "redirect:/tree/search/result/{treeSearchText}";
    }

    @GetMapping("/book/reg")
    public String getBookReg(
            @RequestParam(value = "auto", required = false) boolean auto,
            @RequestParam(value = "id", required = false) Long id,
            Model model
    ) {
        if (auto) {
            return "view/tree/book/reg-auto";
        } else {
            model.addAttribute("categories", BookCategoryType.values());
            return "view/tree/book/reg";
        }
    }

    @PostMapping("/book/reg")
    @ResponseBody
    public Object postBookReg(
            @RequestParam(value = "bookImage", required = false) MultipartFile bookImage
//            @ModelAttribute("tree") Tree tree
    ) {
        return null;
    }

    @GetMapping("/book/edit")
    public String getBookEdit(
            @RequestParam(value = "auto", required = false) boolean auto,
            @RequestParam(value = "id", required = false) Long id,
            Model model
    ) {
        model.addAttribute("categories", BookCategoryType.values());
        return "view/tree/book/edit";
    }

    @PostMapping("/book/edit")
    @ResponseBody
    public Object postBookEdit(
            @RequestParam(value = "bookImage", required = false) MultipartFile bookImage
//            @ModelAttribute("tree") Tree tree
    ) {
        return null;
    }

    @GetMapping("/list")
    public String getBranchList(Model model) {
        return "view/tree/list";
    }

    @GetMapping("/reg-etc")
    public String getTreeEtcReg(
            @RequestParam(value = "type", required = false) String type,
            Model model
    ) {
        // hack type 데이터 로직 어디에 넣을지
        SeedCategoryType seedCategoryType;
        if (!SeedCategoryType.contains(type)) {
            seedCategoryType = SeedCategoryType.IDEA;
        } else {
            seedCategoryType = SeedCategoryType.of(type);
        }

        if (seedCategoryType == SeedCategoryType.BOOK) {
            seedCategoryType = SeedCategoryType.IDEA;
        }

        model.addAttribute("seed", seedCategoryType);
        return "view/tree/reg-etc";
    }

    @PostMapping("/reg-etc")
    public String postTreeEtcReg() {
        return "redirect:/leaf/reg?first=true&seed=seed_id";
    }


    //sortType=  검색 기준
    // t: title 검색
    // a: author 검색
    @GetMapping("/book/select")
    public String searchBook(@ModelAttribute("bookPreviews")List<BookPreviewView> bookPreviews
            ,Model model ,@RequestParam(value = "sort",defaultValue = "t",required = true) String searchType
            ,@RequestParam(value = "target", required = false) String target) {

        if(target != null && searchType.equals("t")){
            List<BookPreviewView> books = bookService.getBooksbyTitle(target);
            model.addAttribute("booksPreviews", books);
            return "view/tree/book/select";
        }

        if(target != null && searchType.equals("a")){
            List<BookPreviewView> books = bookService.getBooksbyAuthor(target);
            model.addAttribute("booksPreviews", books);
            return "view/tree/book/select";
        }

        model.addAttribute("resultCnt", bookPreviews.size());
        return "view/tree/book/select";
    }

    @PostMapping("/book/select")
    public String PostSearchBook(@RequestParam(value = "target", required = false) String target
                                ,RedirectAttributes redirectAttributes){

        return "view/tree/book/select";
    }

    @RequestMapping("/detail/{treeId}")
    public String getTreeDetail(
            Model model,
            @PathVariable(name = "treeId") String treeId
    ) {
        return "view/tree/index";
    }

    @GetMapping("/memoir/register/index")
    public String getmemoirindex(Model model) {
        model.addAttribute("categories", BookCategoryType.values());
        return "view/tree/memoir/register/index";
    }
}