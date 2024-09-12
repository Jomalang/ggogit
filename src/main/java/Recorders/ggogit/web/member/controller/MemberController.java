package Recorders.ggogit.web.member.controller;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.service.LoginService;
import Recorders.ggogit.web.member.form.LoginForm;
import Recorders.ggogit.web.member.form.LoginRegForm;
import Recorders.ggogit.web.member.session.SessionConst;
import Recorders.ggogit.web.member.validation.LoginRegValidator;
import Recorders.ggogit.web.member.validation.LoginValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final LoginService loginService;
    private final LoginRegValidator loginRegValidator;
    private final LoginValidator loginValidator;

    @GetMapping("/login")
    public String getMemberLogin(Model model ,@RequestParam(value = "j", required = false) boolean isNewMember) {
        model.addAttribute("member", new LoginRegForm());
        model.addAttribute("isNewMember", isNewMember);
        return "view/member/index";
    }

    @PostMapping("/login")
    public String postMemberLogin(@Validated @ModelAttribute("member") LoginForm loginForm,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes
                                  , Model model
                                ,HttpServletRequest request
                                ,@RequestParam(value = "redirectURL", required = false) String redirectURL) {

        //공백 검사
        if(bindingResult.hasErrors()){
            log.info("errors: {}", bindingResult.getAllErrors());
            return "view/member/index";
        }

        //유효성 검사
        if(loginValidator.supports(loginForm.getClass())){
            loginValidator.validate(loginForm, bindingResult);
            if(bindingResult.hasErrors()){
                log.info("errors: {}", bindingResult.getAllErrors());
                return "view/member/index";
            }
        }

        Member member = loginForm.toMember();
        Member loginMember = loginService.login(member);

        //로그인 성공
        //세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        //redirectURL이 있다면 로그인 후 바로 원래 페이지로 보내기
        if(redirectURL != null) {
            return"redirect:" + redirectURL;
        }

        redirectAttributes.addAttribute("nickName", loginMember.getNickname());
        return "redirect:/home/{nickName}";
    }

    @PostMapping("/logout")
    public String postMemberLogout(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return"redirect:/member/login";
    }

    @GetMapping("/join-input")
    public String getMemberJoinInput(Model model) {
        model.addAttribute("loginRegForm", new LoginRegForm());
        return "view/member/join-input";
    }

    @PostMapping("/join-input")
    public String PostMemberJoinInput(@Validated @ModelAttribute("loginRegForm") LoginRegForm loginRegForm,
                                      BindingResult bindingResult,RedirectAttributes redirectAttributes){
        //공백 검사
        if(bindingResult.hasErrors()){
            log.info("errors: {}", bindingResult.getAllErrors());
            return "view/member/join-input";
        }

        //유효성 검사
        if(loginRegValidator.supports(loginRegForm.getClass())){
            loginRegValidator.validate(loginRegForm, bindingResult);
            if(bindingResult.hasErrors()){
                log.info("errors: {}", bindingResult.getAllErrors());
                return "view/member/join-input";
            }
        }

        Member newMember = loginRegForm.toMember();

        //회원 가입 성공
        loginService.RegMember(newMember);
        redirectAttributes.addAttribute("j", true);
        return "redirect:/member/login";

    }


    @GetMapping("/join")
    public String getMemberJoin() {
        return "view/member/join";
    }

    @PostMapping("/join")
    public String postMemberJoin() {
        return "view/member/join";
    }

    @GetMapping("/pw/rst")
    public String getMemberPwRst() {
        return "view/member/pw/rst";
    }

    @PostMapping("/pw/rst")
    public String postMemberPwRst() {
        return "view/member/pw/rst";
    }
}
