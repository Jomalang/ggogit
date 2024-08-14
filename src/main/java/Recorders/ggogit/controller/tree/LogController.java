package Recorders.ggogit.controller.tree;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tree/log")
public class LogController {

    @GetMapping("/register/book")
    public String getBookBranch() {
        return "view/tree/log/register/book";
    }
}
