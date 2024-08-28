package Recorders.ggogit.web.book;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping("/detail")
    public String detail(Model model) {

        model.addAttribute("type1", new int[]{1, 1, 1});
        model.addAttribute("type2", new int[]{2, 2, 2});
        model.addAttribute("type3", new int[]{3, 3, 3});
        return "view/book/index";
    }
}
