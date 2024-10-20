package Recorders.ggogit.web.main;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.tree.entity.Seed;
import Recorders.ggogit.domain.tree.service.SeedService;
import Recorders.ggogit.domain.tree.service.TreeService;
import Recorders.ggogit.domain.tree.service.TreeServiceImpl;
import Recorders.ggogit.domain.tree.view.TreeInfoView;
import Recorders.ggogit.web.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private TreeService treeService;
    @Autowired
    private SeedService seedService;

    @GetMapping("/")
    public String index(
            @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) Member member
            , Model model
            , RedirectAttributes redirectAttributes) {
        if(member == null){
            return "index";
        }

        model.addAttribute("member", member);
        redirectAttributes.addAttribute("nickName", member.getNickname());
        return "redirect:/home/{nickName}";


    }

    @GetMapping("/home/{nickname}")
    public String index(HttpServletRequest request,
                        Model model
                        ) {

        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (treeService.getTreeCount(member.getId()) == 0) {
            return "view/home/no-tree";
        } else {
            List<Seed> seedList = seedService.getSeeds();

            List<TreeInfoView> treeInfoList = treeService.getTreeInfoView(member.getId());

            System.out.println(treeInfoList);

            model.addAttribute("treeInfoList", treeInfoList);
            model.addAttribute("seedList", seedList);

            return "view/home/has-tree";
        }
    }
}