package Recorders.ggogit.web.leaf;

import Recorders.ggogit.domain.leaf.service.LeafTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    LeafTagService leafTagService;

    @RequestMapping("/list")
    public String getTagList(
            @RequestParam(value = "id") Long memberId,
            Model model
    ) {
        model.addAttribute("list", leafTagService.getLeafTagViews(memberId));
        return "/view/tag/list";
    }

    @RequestMapping("/edit")
    public String getTagEdit(
            @RequestParam(value = "id") Integer tagId
    ) {
        return "/view/tag/edit";
    }

    @PostMapping("/edit")
    public String postTagEdit(
            @RequestParam(value = "id") Integer tagId
    ) {
        return "redirect:";
    }
}