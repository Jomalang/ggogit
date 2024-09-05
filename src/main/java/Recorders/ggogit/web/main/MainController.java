package Recorders.ggogit.web.main;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.tree.service.MemTreeServiceImpl;
import Recorders.ggogit.domain.tree.view.TreeInfoView;
import Recorders.ggogit.domain.tree.view.MyTreeView;
import Recorders.ggogit.web.member.session.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping()
public class MainController {

    @Autowired
    private MemTreeServiceImpl memTreeService;


    @GetMapping("/")
    public String index(@SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) Member member
            , Model model
            , RedirectAttributes redirectAttributes) {
        if(member == null){
            return "/index";
        }

        model.addAttribute("member", member);
        redirectAttributes.addAttribute("nickName", member.getNickname());
        return "redirect:/home/{nickName}";


    }

    @GetMapping("/home/{nickname}")
    public String index(Model model,
                        @RequestParam(name = "tree", required = false, defaultValue = "true") Boolean memberHasTree) {

        if (!memberHasTree) {
            return "view/home/no-tree";
        } else {


            // 더미 데이터
            // 테스트 위한 트리 리스트(나중에 리포지로 빠짐)

            /*
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
*/
            List<TreeInfoView> treeInfoList = memTreeService.getTreeInfoView(1L);
            List<MyTreeView> treeLists = memTreeService.getMyTreeView(1L);


            model.addAttribute("treeInfoList", treeInfoList);
            model.addAttribute("treeList", treeLists);
            return "view/home/has-tree";
        }
    }
}