package Recorders.ggogit.web.memoir.controller;

import Recorders.ggogit.domain.leaf.service.LeafService;
import Recorders.ggogit.domain.memoir.entity.Memoir;
import Recorders.ggogit.domain.memoir.service.MemoirService;
import Recorders.ggogit.domain.tree.service.TreeService;
import Recorders.ggogit.domain.tree.view.TreeInfoView;
import Recorders.ggogit.web.memoir.form.MemoirForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/memoir")
@RequiredArgsConstructor
@Slf4j
public class MemoirController {

    private final MemoirService memoirService;
    private final TreeService treeService;
    private final LeafService leafService;

    private final String uploadDir = Paths.get("C:", "ggogit", "src", "main", "webapp", "image", "tmp").toAbsolutePath()
            .toString();

    @GetMapping("/index")
    public String getMemoirIndex(Model model, @RequestParam(value = "t") long treeId) {
        Memoir memoir = memoirService.getMemoir(treeId);
        TreeInfoView treeInfo = treeService.getTreeInfoViewByTreeId(treeId);
        model.addAttribute("memoir", memoir);
        model.addAttribute("treeInfo", treeInfo);


        model.addAttribute("memoirCards", new int[]{1, 1, 1});
        model.addAttribute("leafCards", new int[]{2, 2, 2});
        model.addAttribute("treeCards", new int[]{3, 3, 3});

        return "view/memoir/index";
    }

    @GetMapping("/reg")
    public String MemoirRegForm(@RequestParam(value = "t") long treeId, Model model) {
        model.addAttribute("memoirForm", new MemoirForm());
        model.addAttribute("treeId", treeId);

        TreeInfoView treeInfo = treeService.getTreeInfoViewByTreeId(treeId);
        model.addAttribute("treeInfo", treeInfo);

        return "view/memoir/reg";
    }

    @PostMapping("/reg")
    public String regMemoir(@Validated @ModelAttribute("memoirForm") MemoirForm memoirForm,
                            BindingResult bindingResult,
                            @RequestParam("t") long treeId,
                            @RequestParam("fileNames") List<String> fileNames,
                            Model model,
                            RedirectAttributes redirectAttributes
    )
            throws IOException {

        // 오류 검출
        Boolean isValidate = validateMemoir(memoirForm, bindingResult, treeId, fileNames, model);
        if (!isValidate) return "/view/memoir/edit";

        // form 입력받은 데이터 memoir에 채우고, 서비스 이용해 저장
        memoirForm.setTreeId(treeId);

        // 파일 경로 수정
        String content = memoirForm.getText();
        String newContent = content.replaceAll("/tui-editor/image-print\\?filename=", "/uploads/image/memoir/");
        memoirForm.setText(newContent);
        Memoir newMemoir = memoirForm.toMemoir();
        // 이미지 저장
        memoirService.imageSave(fileNames);
        // 최종 세이브
        memoirService.regMemoir(newMemoir);

        log.info("memoirForm = {}", memoirForm);
        redirectAttributes.addAttribute("t",treeId);

        return "redirect:/memoir/index";
    }

    @GetMapping("/edit")
    public String editMemoir(@RequestParam(value = "t") long treeId, Model model) {
        model.addAttribute("treeId", treeId);

        //TODO: 메서드 추출하기
        Memoir memoir = memoirService.getMemoir(treeId);
        MemoirForm memoirForm = new MemoirForm();
        memoirForm.setTitle(memoir.getTitle());
        memoirForm.setText(memoir.getText());
        memoirForm.setTreeId(treeId);
        memoirForm.setVisibility(memoir.getVisibility());
        model.addAttribute("memoirForm", memoirForm);

        TreeInfoView treeInfo = treeService.getTreeInfoViewByTreeId(treeId);
        model.addAttribute("treeInfo", treeInfo);

        return "view/memoir/edit";
    }

    @PostMapping("/edit")
    public String editMemoir(@Validated @ModelAttribute("memoirForm") MemoirForm memoirForm,
                            BindingResult bindingResult,
                            @RequestParam("t") long treeId,
                            @RequestParam("fileNames") List<String> fileNames,
                            Model model,
                            RedirectAttributes redirectAttributes
    )
            throws IOException {

        // 오류 검출
        Boolean isValidate = validateMemoir(memoirForm, bindingResult, treeId, fileNames, model);
        if (!isValidate) return "/view/memoir/edit";

        // 파일 경로 수정
        String content = memoirForm.getText();
        String newContent = content.replaceAll("/tui-editor/image-print\\?filename=", "/uploads/image/memoir/");
        memoirForm.setText(newContent);
        // form 입력받은 데이터 memoir에 채우고, 서비스 이용해 저장
        memoirForm.setTreeId(treeId);
        System.out.println("memoirForm = " + memoirForm);
        //폼객체 전환
        Memoir newMemoir = memoirForm.toMemoir();
        // 이미지 저장
        memoirService.imageSave(fileNames);

        log.info("newMemoir = {}", newMemoir);
        // 최종 수정
        memoirService.modifyMemoir(newMemoir,treeId);
        redirectAttributes.addAttribute("t",treeId);

        return "redirect:/memoir/index";
    }

    private boolean validateMemoir(MemoirForm memoirForm, BindingResult bindingResult, long treeId, List<String> fileNames, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult.getAllErrors());
            model.addAttribute("treeId", treeId);
            TreeInfoView treeInfo = treeService.getTreeInfoViewByTreeId(treeId);
            model.addAttribute("treeInfo", treeInfo);
            model.addAttribute("fileNames", fileNames);
            model.addAttribute("memoirForm", memoirForm);
            log.info(memoirForm.toString());
            return false;
        }
        return true;
    }

}
