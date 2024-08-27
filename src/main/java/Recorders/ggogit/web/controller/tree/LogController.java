package Recorders.ggogit.web.controller.tree;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tree/log")
public class LogController {

    @GetMapping("/register/first/book")
    public String getBookFirstBranch() {
        return "view/tree/log/register/first/book";
    }

    @GetMapping("/register/first/etc")
    public String getEtcFirstBranch() {
        return "view/tree/log/register/first/etc";
    }

    @GetMapping("/register/book")
    public String getBookBranch() {
        return "view/tree/log/register/book";
    }

    @GetMapping("/register/etc")
    public String getEtcBranch() {
        return "view/tree/log/register/etc";
    }

    @GetMapping("/list")
    public String getLogList() {
        return "view/tree/log/list";
    }

    @GetMapping("/register/tag/edit")
    public String getTagEdit() {
        return "view/tree/log/register/tag-edit";
    }
}