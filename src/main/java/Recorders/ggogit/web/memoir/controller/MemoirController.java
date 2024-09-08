package Recorders.ggogit.web.memoir.controller;


import Recorders.ggogit.domain.memoir.entity.Memoir;
import Recorders.ggogit.domain.memoir.repository.MemoirRepository;
import Recorders.ggogit.domain.memoir.service.MemoirService;
import Recorders.ggogit.web.memoir.MemoirForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/memoir")
@RequiredArgsConstructor
@Slf4j
public class MemoirController {

    private final MemoirService memoirService;

    @GetMapping("/index")
    public String getMemoirIndex(Model model) {

        model.addAttribute("type1", new int[]{1, 1, 1});
        model.addAttribute("type2", new int[]{2, 2, 2});
        model.addAttribute("type3", new int[]{3, 3, 3});

        return "view/memoir/index";
    }

    @GetMapping("/reg")
    public String MemoirRegForm(@RequestParam(value = "t") long treeId, Model model) {
        model.addAttribute("memoirForm", new MemoirForm());
        model.addAttribute("treeId", treeId);
        return "view/memoir/reg";
    }

    @PostMapping("/reg")
    public String regMemoir(@Validated @ModelAttribute("memoirForm") MemoirForm memoirForm, BindingResult bindingResult
            , @RequestParam("t")long treeId, Model model) {

        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult.getAllErrors());
            model.addAttribute("treeId", treeId);
            return "view/memoir/reg";
        }
        memoirForm.setTreeId(treeId);
        Memoir newMemoir = memoirForm.toMemoir();
        memoirService.regMemoir(newMemoir);

        log.info("memoirForm = {}", memoirForm);

        return "redirect:/";
    }

}
