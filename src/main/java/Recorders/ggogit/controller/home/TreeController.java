package Recorders.ggogit.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("homeTreeController")
@RequestMapping("/home/tree")
public class TreeController {

    @GetMapping("/search")
    public String treeSearch() {
        return "view/home/tree/search/index";
    }

    @PostMapping("/search")
    public String postMethodName(@RequestParam("treeSearchText") String treeSearchText) {
        System.out.println(treeSearchText);
        return "redirect:/home/tree/search";
    }

}