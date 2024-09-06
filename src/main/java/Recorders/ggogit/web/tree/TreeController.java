package Recorders.ggogit.web.tree;

import Recorders.ggogit.type.BookCategoryType;
import Recorders.ggogit.type.SeedCategoryType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
@RequestMapping("/tree")
public class TreeController {

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

    @GetMapping("/etc/reg")
    public String getTreeEtcReg(
            @RequestParam(value = "type", required = false) String type,
            Model model
    ) {
        String seedName;
        if(type.equals("idea"))
            seedName = "생각";
        else if (type.equals("phrase")) 
            seedName = "문장";
        else if (type.equals("study"))
            seedName = "공부";
        else seedName = "영상";
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
    public String searchBook(Model model) {
        int resultCnt = 0;
        model.addAttribute("resultCnt", resultCnt);
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