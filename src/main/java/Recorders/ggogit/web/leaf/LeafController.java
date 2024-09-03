package Recorders.ggogit.web.leaf;

import Recorders.ggogit.Type.SeedCategoryType;
import Recorders.ggogit.domain.leaf.service.LeafBookService;
import Recorders.ggogit.domain.leaf.service.LeafEtcService;
import Recorders.ggogit.web.leaf.form.LeafFrom;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/leaf")
public class LeafController {

    @Autowired
    private LeafEtcService leafEtcService;

    @Autowired
    private LeafBookService leafBookService;

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
        @RequestParam(value = "leaf_id", required = false) @Nullable Long leafId,
        Model model
    ) {
        // TODO: 트리 조회를 통한 SEED값 조회
        Long seedId = 1L;
        if (SeedCategoryType.isBook(seedId.intValue())) {
            model.addAttribute("list", leafBookService.getLeafItemList(treeId, leafId));
        } else {
            model.addAttribute("list", leafEtcService.getLeafItemList(treeId, leafId));
        }
        return "/view/leaf/list";
    }

    @GetMapping("/detail")
    public String getLeafDetail() {
        return "/view/leaf/list";
    }

}