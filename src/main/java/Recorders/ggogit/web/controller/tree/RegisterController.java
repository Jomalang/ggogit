package Recorders.ggogit.web.controller.tree;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Recorders.ggogit.entity.SeedCategoryType;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping("/seed")
    public String selectSeed(Model model) {
        model.addAttribute("seeds", SeedCategoryType.values());
        return "view/tree/register/0-index";
    }

    @GetMapping("/book")
    public String searchBook(Model model) {
        int resultCnt = 0;
        model.addAttribute("resultCnt", resultCnt);
        return "view/tree/register/3-book-search";
    }

}
