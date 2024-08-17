package Recorders.ggogit.controller.tree;

import Recorders.ggogit.entity.Branch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/tree/index")
    public String getBranchList(Model model) {
        List<Branch> lists2 = new ArrayList<>();
        lists2.add(new Branch("heegwon-branch","card-bookmark-icon.svg","2024-07-18","브랜치 이름",999,90));
        lists2.add(new Branch("taegyu-branch","card-bookmark-icon.svg","2024-07-18","브랜치 이름",999,90));
        lists2.add(new Branch("hyeonjin-branch","card-tree-icon.svg","2024-07-18","브랜치 이름",999,90));
        lists2.add(new Branch("jinpeal-branch","card-bookmark-icon.svg","2024-07-18","브랜치 이름",999,90));
        lists2.add(new Branch("jaeyoung-branch","card-bookmark-icon.svg","2024-07-18","브랜치 이름",999,90));
        model.addAttribute("lists", lists2);
        return "view/user/tree/index";
    }
}