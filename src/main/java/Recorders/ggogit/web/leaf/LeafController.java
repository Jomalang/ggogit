package Recorders.ggogit.web.leaf;

import Recorders.ggogit.Type.SeedCategoryType;
import Recorders.ggogit.domain.leaf.service.LeafBookService;
import Recorders.ggogit.domain.leaf.service.LeafService;
import Recorders.ggogit.web.leaf.form.LeafFrom;
import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/leaf")
public class LeafController {

    @Autowired
    private LeafService leafService;

    @Autowired
    private LeafBookService leafBookService;

    @GetMapping("/first/reg")
    public ModelAndView getFirstReg(
            @RequestParam(value = "seed", required = false) Integer seed
    ) {
        ModelAndView mv;
        if (SeedCategoryType.isBook(seed)) {
            mv = new ModelAndView("/view/leaf/1st-reg-book");
            mv.addObject("form", new LeafFrom());
        } else {
            mv = new ModelAndView("/view/leaf/1st-reg-etc");
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
        if (SeedCategoryType.isBook(seed)) {
            return "/view/leaf/reg-book";
        }
        return "/view/leaf/reg-etc";
    }

    @PostMapping("/reg")
    public String postReg() {
        return "redirect:/leaf/list";
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
    public String getLeafList() {
        return "/view/leaf/list";
    }

    @GetMapping("/detail")
    public String getLeafDetail() {
        return "/view/leaf/list";
    }

}