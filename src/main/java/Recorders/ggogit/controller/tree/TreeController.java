package Recorders.ggogit.controller.tree;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tree")
public class TreeController {

    @GetMapping("/book/reg")
    public String treeSearch() {
        return "view/tree/book/reg";
    }
}