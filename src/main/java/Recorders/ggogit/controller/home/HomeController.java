package Recorders.ggogit.controller.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Recorders.ggogit.entity.Tree;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/no")
    public String indexNoTree() {
        return "view/home/no-tree";
    }

    @GetMapping
    public String index(Model model) {
        // 더미 데이터
        // 테스트 위한 트리 리스트(나중에 리포지로 빠짐)
        List<Tree> trees = new ArrayList<>();
        long id = 1L;
        for (int i = 0; i < 2; i++) {
            Tree t1 = Tree.createTestTree();
            t1.setImgUrl("book-cover-dummy1.svg");
            t1.setId(id++);
            trees.add(t1);
        }
        for (int i = 0; i < 2; i++) {
            Tree t1 = Tree.createTestTree();
            t1.setId(id++);
            t1.setBookName("testBook2");
            t1.setImgUrl("book-cover-dummy2.svg");
            trees.add(t1);
        }
        for (int i = 0; i < 2; i++) {
            Tree t1 = Tree.createTestTree();
            t1.setId(id++);
            t1.setBookName("testBook3");
            t1.setImgUrl("book-cover-dummy3.svg");
            trees.add(t1);
        }

        model.addAttribute("treeList", trees);
        return "view/home/index";
    }

}