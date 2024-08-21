package Recorders.ggogit.controller.login;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login() {
        return "view/login/index";
    }

    @GetMapping("join")
    public String join() {
        return "view/login/join";
    }
}
