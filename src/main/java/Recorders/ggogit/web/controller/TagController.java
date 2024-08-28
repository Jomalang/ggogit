package Recorders.ggogit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tag")
public class TagController {

    @RequestMapping("/list")
    public String getTagList() {
        return "/view/tag/list";
    }
    @RequestMapping("/edit")
    public String getTagEdit(
            @RequestParam(value = "id", required = false) Integer id
    ) {
        return "/view/tag/edit";
    }
    @PostMapping("/edit")
    public String postTagEdit(
            @RequestParam(value = "id", required = false) Integer id
    ) {
        return "redirect:";
    }
}
