package Recorders.ggogit.web.leaf;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.service.LeafTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("WebLeafTagController")
@RequestMapping("/tag")
public class TagController {

    @Autowired
    LeafTagService leafTagService;

    @GetMapping("/list")
    public String getTagList(
            @RequestParam(value = "id", required = false) Long memberId, // TODO: 계정 필터 적용해야함
            @RequestParam(value = "page", defaultValue = "1") Long page,
            @RequestParam(value = "size", defaultValue = "10") Long size,
            Model model
    ) {
        model.addAttribute("list", leafTagService.getLeafTags(memberId, page, size));
        return "/view/tag/list";
    }

    @GetMapping("/edit")
    public String getTagEdit(
            @RequestParam(value = "id") Long tagId,
            Model model
    ) {
        LeafTag leafTag = leafTagService.getLeafTag(tagId);
        model.addAttribute("memberId", leafTag.getMemberId());
        model.addAttribute("tag", leafTag);
        return "/view/tag/edit";
    }

    @PostMapping("/edit")
    public String postTagEdit(
            @RequestParam(value = "id") Long tagId
    ) {
        Long memberId = leafTagService.getLeafTag(tagId).getMemberId();
        return "redirect:/tag/list?id=" + memberId;
    }

    @PostMapping("/delete")
    public String deleteTag(
            @RequestParam(value = "id") Long tagId
    ) {
        Long memberId = leafTagService.getLeafTag(tagId).getMemberId();
        leafTagService.remove(memberId, tagId);
        return "redirect:/tag/list?id=" + memberId;
    }
}