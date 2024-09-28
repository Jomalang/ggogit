package Recorders.ggogit.api.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/aladin")
public class AladinController {

    @Autowired
    private AladinService aladinService;

    @GetMapping("")
    public String showSearchForm() {
        return "view/book/aladin";
    }

    @PostMapping("/search")
    public String searchBooks(@RequestParam String keyword) {
        aladinService.saveBooks(keyword);
        return "redirect:/aladin";
    }
}
