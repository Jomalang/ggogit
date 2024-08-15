package Recorders.ggogit.controller.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Recorders.ggogit.entity.Tree;

@Controller("homeTreeController")
@RequestMapping("/home/tree")
public class TreeController {

    @GetMapping("/search")
    public String treeSearch() {
        return "view/home/tree/search/index";
    }

    @PostMapping("/search")
    public String treeSearch(@RequestParam("treeSearchText") String treeSearchText,
            RedirectAttributes redirectAttributes) {
        System.out.println(treeSearchText);
        redirectAttributes.addAttribute("treeSearchText", treeSearchText);
        return "redirect:/home/tree/search/result/{treeSearchText}";
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

        return "view/home/tree/search/tree-list";
    }

    @PostMapping("/search/result/{treeSearchText}")
    public String treeSearchResult(@RequestParam("treeSearchText") String treeSearchText,
            RedirectAttributes redirectAttributes) {
        System.out.println(treeSearchText);
        redirectAttributes.addAttribute("treeSearchText", treeSearchText);
        return "redirect:/home/tree/search/result/{treeSearchText}";
    }

}