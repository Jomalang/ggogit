package Recorders.ggogit.controller.tree;

import Recorders.ggogit.entity.BookCategoryType;
import Recorders.ggogit.entity.Branch;
import Recorders.ggogit.entity.SeedCategoryType;
import Recorders.ggogit.entity.Tree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tree")
public class TreeController {

    @GetMapping("/book/reg")
    public String getReg(Model model) {
        model.addAttribute("categories", BookCategoryType.values());
        return "view/tree/book/reg";
    }

    @PostMapping("/book/reg")
    @ResponseBody
    public Object postReg(
            @RequestParam(value = "bookImage", required = false) MultipartFile bookImage,
            @ModelAttribute("tree") Tree tree
    ) {
        if (bookImage.isEmpty()) {
            System.out.println("이미지가 없습니다.");
        }
        System.out.println(tree.toString());
        return tree;
    }

    @GetMapping("/etc/reg")
    public String getEtcReg(
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
        return "view/tree/etc/reg";
    }

    @GetMapping("/memoir/register/index")
    public String getmemoirindex(Model model) {
        model.addAttribute("categories", BookCategoryType.values());
        return "view/tree/memoir/register/index";
    }
    @GetMapping("/book/reg4-search-book")
    public String getReg4SearchBook(Model model) {
        model.addAttribute("categories", BookCategoryType.values());
        return "view/tree/register/4-reg-search-book";
    }
    @GetMapping("/branch/list")
    public String getBranchList(Model model) {
        List<Branch> lists = new ArrayList<>();
        lists.add(new Branch("heegwon-branch","card-bookmark-icon.svg","2024-07-18","브랜치 이름",999,90));
        lists.add(new Branch("taegyu-branch","card-bookmark-icon.svg","2024-07-18","브랜치 이름",999,90));
        lists.add(new Branch("hyeonjin-branch","card-tree-icon.svg","2024-07-18","브랜치 이름",999,90));
        lists.add(new Branch("jinpeal-branch","card-bookmark-icon.svg","2024-07-18","브랜치 이름",999,90));
        lists.add(new Branch("jaeyoung-branch","card-bookmark-icon.svg","2024-07-18","브랜치 이름",999,90));
        model.addAttribute("lists", lists);
        return "view/tree/branch/list";
    }
}