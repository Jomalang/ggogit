package Recorders.ggogit.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Recorders.ggogit.entity.BookCategoryType;
import Recorders.ggogit.entity.Branch;
import Recorders.ggogit.entity.SeedCategoryType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Recorders.ggogit.entity.Tree;

@Controller("homeTreeController")
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
        // 더미 데이터
        // 검색 결과 담는 map
        Map<Long, Tree> map = new HashMap<>();
        // 테스트 위한 트리 리스트(나중에 리포지로 빠짐)
        List<Tree> trees = new ArrayList<>();
        long id = 1L;
        for (int i = 0; i < 5; i++) {
            Tree t1 = Tree.createTestTree();
            t1.setId(id++);
            trees.add(t1);
        }
        for (int i = 0; i < 3; i++) {
            Tree t1 = Tree.createTestTree();
            t1.setId(id++);
            t1.setBookName("testBook2");
            trees.add(t1);
        }

        // 트리 찾는 과정(나중에 서비스로 빠짐)
        for (Tree t : trees) {
            if (t.getBookName().equals(treeSearchText)) {
                map.put(t.getId(), t);
                System.out.println(t.getId());
            }
        }

        model.addAttribute("resultMap", map);

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
    @GetMapping("/reg-inspiration")
    public String getTreeInspirationReg() {
        return "view/tree/reg-inspiration";
    }
    @GetMapping("/reg-video")
    public String getTreeVideoReg() {
        return "view/tree/reg-video";
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
}