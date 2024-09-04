package Recorders.ggogit.web.member.controller;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.service.LoginService;
import Recorders.ggogit.web.member.form.LoginForm;
import Recorders.ggogit.web.member.form.LoginRegForm;
import Recorders.ggogit.web.member.session.SessionConst;
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

        //입력에 오류가 있을 경우(빈칸 등)
        //TODO 이메일 형식 검증 로직 필요
        if (bindingResult.hasErrors()) {
            log.info("errors: {}", bindingResult.getAllErrors());
            return "/view/member/index";
        }

        Member loginMember = loginService.login(loginForm);
        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            log.info("errors: {}", bindingResult.getGlobalErrors());
            return "/view/member/index";
        }

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
        return"redirect:/home/index";
    }

    @GetMapping("/join-input")
    public String getMemberJoinInput(Model model) {
        model.addAttribute("loginRegForm", new LoginRegForm());
        return "view/member/join-input";
    }

    @PostMapping("/join-input")
    public String PostMemberJoinInput(@Validated @ModelAttribute("loginRegForm") LoginRegForm loginRegForm,
                                      BindingResult bindingResult,RedirectAttributes redirectAttributes){

        //검증 로직
        //TODO Validation 계층 따로 만들기
        if(bindingResult.hasErrors()) {
            log.info("errors: {}", bindingResult.getAllErrors());
            return "/view/member/join-input";
        }


        //동의 하지 않은 경우 검증
        if(!loginRegForm.getPolicyAgreement()){
            bindingResult.rejectValue("policyAgreement", "NotAgree");
            log.info("errors: {}", bindingResult.getAllErrors());
            return "/view/member/join-input";
        }

        //TODO Email 형식 검증해야 함.
        {

        }

        //중복되는 이메일 검증
        //TODO 이메일 중복 검사 버튼 만들기
        {
            String email = loginRegForm.getEmail();
            if(loginService.getMemberByEmail(email) != null){
                bindingResult.rejectValue("email","Duplicate");
                log.info("errors: {}", bindingResult.getAllErrors());
                return "/view/member/join-input";
            }
        }

        //중복되는 닉네임 검증
        {
            String nickname = loginRegForm.getNickname();
            if(loginService.getMemberBynickname(nickname) != null){
                bindingResult.rejectValue("nickname","Duplicate");
                log.info("errors: {}", bindingResult.getAllErrors());
                return "/view/member/join-input";
            }
        }

        //회원 가입 성공
        Member newMember = loginService.RegMember(loginRegForm);
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
