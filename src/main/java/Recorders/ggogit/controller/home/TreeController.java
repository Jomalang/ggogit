package Recorders.ggogit.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("homeTreeController")
@RequestMapping("/home/tree")
public class TreeController {

    @GetMapping("/search")
    public String treeSearch() {
        return "view/home/tree/search/index";
    }
}