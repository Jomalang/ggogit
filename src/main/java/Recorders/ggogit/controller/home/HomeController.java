package Recorders.ggogit.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    
    // 임시 경로
    @GetMapping
    public String index() {
        return "view/home/no-tree";
    }

    // 임시 경로
    @GetMapping("/tree/search")
    public String treeSearch() {
        return "view/home/tree/search/index";
    }

}
