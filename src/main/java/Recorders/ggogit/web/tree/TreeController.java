package Recorders.ggogit.web.tree;

import Recorders.ggogit.domain.book.entity.Book;
import Recorders.ggogit.domain.book.service.BookService;
import Recorders.ggogit.domain.book.view.BookDetailView;
import Recorders.ggogit.domain.book.view.BookInfoView;
import Recorders.ggogit.domain.book.view.BookPreviewView;
import Recorders.ggogit.domain.tree.service.TreeServiceImpl;
import Recorders.ggogit.web.book.form.bookSearchType;
import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.service.TreeService;
import Recorders.ggogit.domain.tree.view.BookTreeView;
import Recorders.ggogit.type.BookCategoryType;
import Recorders.ggogit.type.SeedCategoryType;
import Recorders.ggogit.web.tree.form.TreeSaveTmpForm;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.io.File;

@Controller()
@RequestMapping("/tree")
@RequiredArgsConstructor
public class TreeController {

    @Autowired
    private TreeServiceImpl treeService;

    @Autowired
    private BookService bookService;

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
            BookInfoView book = bookService.getBookbyId(id);

            System.out.println(book.toString());
            model.addAttribute("book", book);
            return "view/tree/book/reg-auto";
        } else {
            model.addAttribute("categories", BookCategoryType.values());
            return "view/tree/book/reg";
        }
    }

    @PostMapping("/book/reg")
    @ResponseBody
    public Object postBookReg(
            @RequestParam(required = false) MultipartFile img,
            @ModelAttribute("form")TreeSaveTmpForm form,
            @RequestParam(value = "auto", required = false) boolean auto,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request
            ) {

        System.out.println(form.toString());
        treeService.tmpTreeSave(form);

        if(!auto){

            String path = request.getServletContext().getRealPath("/image/tmp");
            String fileName = img.getOriginalFilename();
            System.out.println(fileName);
            String filePath = path + File.separator + fileName;

            // 경로가 존재하지 않으면 생성
            try {
                Path dirPath = Paths.get(path);
                Files.createDirectories(dirPath);

                // 파일 저장
                File destFile = new File(filePath);
                img.transferTo(destFile);
            } catch (IOException e) {
                // 디렉토리 생성 중 오류 발생 시 처리
                e.printStackTrace();
                // 적절한 오류 처리 로직 추가
            }
        }
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

    @GetMapping("/etc/reg")
    public String getTreeEtcReg(
            @RequestParam(value = "type", required = false) String type,
            Model model
    ) {
        String seedName = switch (type) {
            case "idea" -> "생각";
            case "phrase" -> "문장";
            case "study" -> "공부";
            default -> "영상";
        };
        // hack type 데이터 로직 어디에 넣을지
//        SeedCategoryType seedCategoryType;
//        if (!SeedCategoryType.contains(type)) {
//            seedCategoryType = SeedCategoryType.IDEA;
//        } else {
//            seedCategoryType = SeedCategoryType.of(type);
//        }
//
//        if (seedCategoryType == SeedCategoryType.BOOK) {
//            seedCategoryType = SeedCategoryType.IDEA;
//        }

        model.addAttribute("seed", seedName);
        return "view/tree/reg-etc";
    }

    @PostMapping("/reg-etc")
    public String postTreeEtcReg() {
        return "redirect:/leaf/reg?first=true&seed=seed_id";
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
            @PathVariable(name = "treeId") String treeId
    ) {
        return "view/tree/index";
    }

    @GetMapping("/memoir/register/index")
    public String getmemoirindex(Model model) {
        model.addAttribute("categories", BookCategoryType.values());
        return "view/memoir/index";
    }
}