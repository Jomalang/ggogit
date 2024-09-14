package Recorders.ggogit.web.leaf;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.service.LeafTagService;
import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.web.leaf.form.LeafTagForm;
import Recorders.ggogit.web.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("WebLeafTagController")
@RequestMapping("/tag")
public class TagController {

    @Autowired
    LeafTagService leafTagService;

    @GetMapping("/list")
    public String getTagList(
            @RequestParam(value = "page", defaultValue = "1") Long page,
            @RequestParam(value = "size", defaultValue = "10") Long size,
            HttpServletRequest request,
            Model model
    ) {
//        Member member = (Member) request.getSession()
//                .getAttribute(SessionConst.LOGIN_MEMBER);

        model.addAttribute("selectedList", List.of());
        model.addAttribute("list", leafTagService.getLeafTags(1L, page, size));
        return "view/tag/list";
    }

    @GetMapping("/edit")
    public String getTagEdit(
            @RequestParam(value = "id") Long tagId,
            Model model
    ) {
        LeafTag leafTag = leafTagService.getLeafTag(tagId);
        model.addAttribute("leafTagForm", new LeafTagForm());
        model.addAttribute("memberId", leafTag.getMemberId());
        model.addAttribute("tag", leafTag);
        return "view/tag/edit";
    }

    @PostMapping("/edit")
    public String postTagEdit(
            @Validated @ModelAttribute("leafTag") LeafTagForm leafTagFrom
    ) {
        // TODO: 계정 소유 여부 필터 적용해야함
        LeafTag saved = leafTagService.modify(leafTagFrom.toEntity());
        return "redirect:/tag/list?id=" + saved.getMemberId();
    }

    @PostMapping("/delete")
    public String deleteTag(
            @RequestParam(value = "id") Long tagId,
            HttpSession session
    ) {
        // Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        leafTagService.remove(1L, tagId);
        return "redirect:/tag/list";
    }
}