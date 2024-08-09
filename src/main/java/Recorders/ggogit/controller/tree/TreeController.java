package Recorders.ggogit.controller.tree;

import Recorders.ggogit.entity.BookCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tree")
public class TreeController {

    @GetMapping("/book/reg")
    public String getReg(Model model) {
        model.addAttribute("categories", BookCategory.values());
        return "view/tree/book/reg";
    }
//    @PostMapping("/book/reg")
//    public String postReg(
//    ) {
//
//    }
}