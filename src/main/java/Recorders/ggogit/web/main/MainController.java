package Recorders.ggogit.web.main;

import java.util.ArrayList;
import java.util.List;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.web.member.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping()
public class MainController {

    @GetMapping("/")
    public String index(@SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false)Member member
            , Model model
            , RedirectAttributes redirectAttributes) {
        if(member == null){
            return "/index";
        }

        model.addAttribute("member", member);
        redirectAttributes.addAttribute("nickName", member.getNickname());
        return "redirect:/home/{nickName}";


    }

    @GetMapping("/home")
    public String index(Model model,
                        @RequestParam(name = "tree", required = false, defaultValue = "true") Boolean memberHasTree) {

        if (!memberHasTree) {
            return "view/home/no-tree";
        } else {
            return "view/home/has-tree";
        }
    }
}