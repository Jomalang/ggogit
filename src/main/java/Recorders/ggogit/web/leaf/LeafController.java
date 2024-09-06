package Recorders.ggogit.web.leaf;

import Recorders.ggogit.type.SeedCategoryType;
import Recorders.ggogit.domain.leaf.service.LeafBookService;
import Recorders.ggogit.domain.leaf.service.LeafEtcService;
import Recorders.ggogit.domain.leaf.service.LeafService;
import Recorders.ggogit.domain.leaf.view.LeafImageCardView;
import Recorders.ggogit.domain.leaf.view.LeafItemView;
import Recorders.ggogit.web.leaf.form.LeafBookForm;
import Recorders.ggogit.web.leaf.form.LeafForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("WebLeafController")
@RequestMapping("/leaf")
public class LeafController {

    @Autowired
    private LeafEtcService leafEtcService;

    @Autowired
    private LeafBookService leafBookService;

    @Autowired
    private LeafService leafService;

    @GetMapping("/first/reg")
    public ModelAndView firstReg(
            @RequestParam(value = "seed", required = false) Long seed
    ) {

        if (!SeedCategoryType.contains(seed)) {
            // TODO: 나중에 예외 처리 해야함
            throw new IllegalArgumentException("잘못된 SeedCategoryType 인자를 받았습니다.");
        }

        ModelAndView mv;
        if (SeedCategoryType.isBook(seed)) {
            mv = new ModelAndView("/view/leaf/1st-reg-book");
            mv.addObject("form", new LeafBookForm());
        } else {
            mv = new ModelAndView("/view/leaf/1st-reg-etc");
            mv.addObject("seed", seed);
            mv.addObject("form", new LeafForm());
        }
        mv.addObject("memberId", 1L);
        return mv;
    }

    @PostMapping("/first/reg")
    public ModelAndView firstReg(
            @Valid @ModelAttribute("form") LeafForm form,
            BindingResult bindingResult
    ) {
        if (SeedCategoryType.BOOK == form.getSeed()) { // 도서 리프 에러 처리
            if (bindingResult.hasErrors()) {
                return new ModelAndView("/view/leaf/1st-reg-book", "form", form);
            }
        }

        if (bindingResult.hasErrors()) { // ETC 리프 에러 처리
            return new ModelAndView("/view/leaf/1st-reg-etc", "form", form);
        }

        return new ModelAndView("redirect:/leaf/list?tree_id=1&leaf_id=1");
    }

    @GetMapping("/reg")
    public ModelAndView reg(
            @RequestParam(value = "leaf_id") Long leafId,
            @RequestParam(value = "seed") Long seed
    ) {

        if (!SeedCategoryType.contains(seed)) {
            // TODO: 나중에 예외 처리 해야함
            throw new IllegalArgumentException("잘못된 SeedCategoryType 인자를 받았습니다.");
        }

        if (SeedCategoryType.isBook(seed)) {
            ModelAndView mv =
                    new ModelAndView("/view/leaf/reg-book", "form", new LeafBookForm());
            mv.addObject("memberId", 1L); // TODO: 나중에 로그인한 사용자 정보로 변경
            return mv;
        }

        ModelAndView mv =
                new ModelAndView("/view/leaf/reg-etc", "form", new LeafForm());
        mv.addObject("memberId", 1L); // TODO: 나중에 로그인한 사용자 정보로 변경
        return mv;
    }

    @PostMapping("/reg")
    public ModelAndView reg(
            @Valid @ModelAttribute("form") LeafForm form,
            BindingResult bindingResult
    ) {

        if (SeedCategoryType.BOOK == form.getSeed()) { // 도서 리프 에러 처리
            if (bindingResult.hasErrors()) {
                return new ModelAndView("/view/leaf/reg-book", "form", form);
            }
        }

        if (bindingResult.hasErrors()) { // ETC 리프 에러 처리
            return new ModelAndView("/view/leaf/reg-etc", "form", form);
        }

        return new ModelAndView("redirect:/leaf/list");
    }

    @GetMapping("/edit")
    public ModelAndView edit(
            @RequestParam(value = "seed", required = false) Long seed,
            @RequestParam(value = "id", required = false) Long id,
            Model model
    ) {
        if (SeedCategoryType.isBook(seed)) {
            return new ModelAndView("/view/leaf/edit-book","form", leafBookService.get(id));
        } else {
            return new ModelAndView("/view/leaf/edit-etc","form", leafEtcService.get(id));
        }
    }

    @GetMapping("/list")
    public String list(
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