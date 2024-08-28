package Recorders.ggogit.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Recorders.ggogit.entity.Tree;

@Controller
@RequestMapping()
public class MainController {

    @GetMapping("/home")
    public String index(Model model) {

        boolean memberHasTree = true;
        if(memberHasTree){
            return "view/home/no-tree";
        }else {
            // 더미 데이터
            // 테스트 위한 트리 리스트(나중에 리포지로 빠짐)
            List<Tree> trees = new ArrayList<>();
            long id = 1L;
            for (int i = 0; i < 1; i++) {
                Tree t1 = Tree.createTestTree();
                t1.setImgUrl("book-cover-dummy1.svg");
                t1.setId(id++);
                t1.setTotalPage(200);
                t1.setReadingPage(100);
                t1.setLeaf(100);
                t1.setLike(200);
                t1.setView(300);
                t1.setDescription("칼릴 지브란의 책을 읽고 정리한 트리입니다.");
                trees.add(t1);
            }
            for (int i = 0; i < 1; i++) {
                Tree t1 = Tree.createTestTree();
                t1.setId(id++);
                t1.setBookName("testBook2");
                t1.setImgUrl("book-cover-dummy2.svg");
                t1.setTotalPage(300);
                t1.setReadingPage(100);
                t1.setLeaf(200);
                t1.setLike(300);
                t1.setView(400);
                t1.setDescription("은희경의 빛의 과거를 읽고 정리한 트리입니다.");
                trees.add(t1);
            }
            for (int i = 0; i < 1; i++) {
                Tree t1 = Tree.createTestTree();
                t1.setId(id++);
                t1.setBookName("testBook3");
                t1.setImgUrl("book-cover-dummy3.svg");
                t1.setTotalPage(400);
                t1.setReadingPage(50);
                t1.setLeaf(300);
                t1.setLike(400);
                t1.setView(500);
                t1.setDescription("헤르만 헤세의 싯다르타를 읽고 정리한 트리입니다.");
                trees.add(t1);
            }

            model.addAttribute("treeList", trees);
            return "view/home/has-tree";
        }
    }
}