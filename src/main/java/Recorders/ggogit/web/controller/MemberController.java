package Recorders.ggogit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String getMemberLogin() {
        return "view/member/index";
    }

    @PostMapping("/login")
    public String postMemberLogin(){ return "redirect:/member/login"; }

    @GetMapping("/join")
    public String getMemberJoin() {
        return "view/member/join";
    }

    @PostMapping("/join")
    public String postMemberJoin() {
        return "view/member/join";
    }

    @GetMapping("/pw/rst")
    public String getMemberPwRst() { return "view/member/pw/rst"; }
    @PostMapping("/pw/rst")
    public String postMemberPwRst() { return "view/member/pw/rst"; }
}
