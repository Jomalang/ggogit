package Recorders.ggogit.controller.filter;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class FilterControllerTest {

    @ModelAttribute("treeFilters")
    public Map<String, String> TreeFilters() {
        Map<String, String> treeFilters = new LinkedHashMap<>();
        treeFilters.put("BOOK", "도서");
        treeFilters.put("YOUTUBE", "유튜브");
        treeFilters.put("IDEA", "영감");
        treeFilters.put("COMPLETE", "완독");

        return treeFilters;
    }

    @GetMapping("/filter/test")
    public String filterTest(Model model) {
        return "/component/hyeonjin/filter/filter";
    }

}
