package Recorders.ggogit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String memberLogin() {
        return "view/member/index";
    }

    @GetMapping("/join")
    public String memberJoin() {
        return "view/member/join";
    }
}
