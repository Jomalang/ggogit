package Recorders.ggogit.web.leaf;

import Recorders.ggogit.type.SeedCategoryType;
import Recorders.ggogit.domain.leaf.service.LeafBookService;
import Recorders.ggogit.domain.leaf.service.LeafEtcService;
import Recorders.ggogit.domain.leaf.service.LeafService;
import Recorders.ggogit.domain.leaf.view.LeafImageCardView;
import Recorders.ggogit.domain.leaf.view.LeafItemView;
import Recorders.ggogit.web.leaf.form.LeafFrom;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/leaf")
public class LeafController {

    @Autowired
    private LeafEtcService leafEtcService;

    @Autowired
    private LeafBookService leafBookService;

    @Autowired
    private LeafService leafService;

    @GetMapping("/first/reg")
    public ModelAndView getFirstReg(
            @RequestParam(value = "seed", required = false) Integer seed
    ) {

        if (!SeedCategoryType.contains(seed)) {
            // TODO: 나중에 예외 처리 해야함
            throw new IllegalArgumentException("잘못된 SeedCategoryType 인자를 받았습니다.");
        }

        ModelAndView mv;
        if (SeedCategoryType.isBook(seed)) {
            mv = new ModelAndView("/view/leaf/1st-reg-book");
            mv.addObject("form", new LeafFrom());
        } else {
            mv = new ModelAndView("/view/leaf/1st-reg-etc");
            mv.addObject("seed", seed);
            mv.addObject("form", new LeafFrom());
        }
        return mv;
    }

    @PostMapping("/first/reg")
    public ModelAndView postFirstReg(
            @Valid @ModelAttribute("form") LeafFrom form,
            BindingResult bindingResult
    ) {
        if (SeedCategoryType.BOOK == form.getSeed()) {
            if (bindingResult.hasErrors()) {
                return new ModelAndView("/view/leaf/1st-reg-book", "form", form);
            }
        } else {
            if (bindingResult.hasErrors()) {
                return new ModelAndView("/view/leaf/1st-reg-etc", "form", form);
            }
        }
        return new ModelAndView("redirect:/leaf/list");
    }

    @GetMapping("/reg")
    public String getReg(
            @RequestParam(value = "seed", required = false) Integer seed
    ) {

        if (!SeedCategoryType.contains(seed)) {
            // TODO: 나중에 예외 처리 해야함
            throw new IllegalArgumentException("잘못된 SeedCategoryType 인자를 받았습니다.");
        }

        if (SeedCategoryType.isBook(seed)) {
            return "/view/leaf/reg-book";
        }
        return "/view/leaf/reg-etc";
    }

    @PostMapping("/reg")
    public ModelAndView postReg(
            @Valid @ModelAttribute("form") LeafFrom form,
            BindingResult bindingResult
    ) {

        if (SeedCategoryType.BOOK == form.getSeed()) {
            if (bindingResult.hasErrors()) {
                return new ModelAndView("/view/leaf/reg-book", "form", form);
            }
        } else {
            if (bindingResult.hasErrors()) {
                return new ModelAndView("/view/leaf/reg-etc", "form", form);
            }
        }

        return new ModelAndView("redirect:/leaf/list");
    }

    @GetMapping("/edit")
    public String getLeafEdit(
            @RequestParam(value = "seed", required = false) Integer seed,
            @RequestParam(value = "id", required = false) Integer id,
            Model model
    ) {
        if (SeedCategoryType.isBook(seed)) {
            return "/view/leaf/edit-book";
        } else {
            return "/view/leaf/edit-etc";
        }
    }

    @GetMapping("/list")
    public String getList(
        @RequestParam(value = "tree_id") Long treeId,
        @RequestParam(value = "leaf_id") Long leafId,
        Model model
    ) {
        List<LeafItemView> list = leafService.getLeafItems(treeId, leafId);
        for (LeafItemView item : list) {
            if (item.getFocused()) {
                model.addAttribute("focusedTime", item.getCreateTime());
                break;
            }
        }
        // 최근 수정 브랜치 이름 정보 넣어야함
        model.addAttribute("recentBranch", leafService.getRecentBranch(treeId));
        model.addAttribute("breadcrumb", leafService.getBreadcrumb(treeId, leafId));
        model.addAttribute("list", list);
        return "/view/leaf/list";
    }

    @GetMapping("/detail")
    public String getLeafDetail(
            @RequestParam(value = "tree_id") Long treeId,
            @RequestParam(value = "leaf_id") Long leafId,
            Model model
    ) {
        model.addAttribute("breadcrumb", leafService.getBreadcrumb(treeId, leafId));
        // TODO: 도서 상세 정보 넣어야함

        Long memberId = 1L;
        LeafImageCardView leafCardView = leafService.LeafImageCardView(memberId);
        model.addAttribute("leafCards", leafCardView);
        return "/view/leaf/list";
    }
}