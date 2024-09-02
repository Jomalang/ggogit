package Recorders.ggogit.web.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class MainController {

    @GetMapping("/home")
    public String index(Model model,
                        @RequestParam(name = "tree", required = false, defaultValue = "true") Boolean memberHasTree) {

            return "view/home/has-tree";
    }
}
