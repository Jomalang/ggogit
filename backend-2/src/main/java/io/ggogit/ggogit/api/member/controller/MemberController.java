package io.ggogit.ggogit.api.member.controller;

import io.ggogit.ggogit.api.member.dto.LoginRequest;
import io.ggogit.ggogit.api.member.dto.LoginResponse;
import io.ggogit.ggogit.api.member.validation.LoginRegValidator;
import io.ggogit.ggogit.api.member.validation.LoginValidator;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.EmailService;
import io.ggogit.ggogit.domain.member.service.LoginService;
import io.ggogit.ggogit.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final LoginService loginService;
    private final EmailService emailService;
    private final LoginRegValidator loginRegValidator;
    private final LoginValidator loginValidator;
    private final MemberService memberService;


    @GetMapping("/login")
    public ResponseEntity<LoginResponse> getMemberLogin(@RequestParam(value = "j", required = false) Boolean isNewMember) {
        LoginResponse response = memberService.getMemberLogin(isNewMember);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> postMemberLogin(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            // 로그인 처리
            Member loginMember = memberService.login(loginRequest.getUsername(), loginRequest.getPassword());

            // 세션 생성 및 사용자 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_MEMBER", loginMember);

            // 성공 응답 반환
            LoginResponse response = new LoginResponse();
            response.setNickname(loginMember.getNickname());
            response.setMessage("Login successful");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new LoginResponse("Error", e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/join-input")
    public ResponseEntity<LoginResponse> getMemberJoinInput(HttpServletRequest request) {
        LoginResponse loginResponse = new LoginResponse();

        // 이메일 쿠키에서 이메일 값을 가져옵니다.
        Optional<String> emailCookie = memberService.getEmailFromCookie(request);

        // 쿠키가 존재하면 이메일 값을 설정합니다.
        emailCookie.ifPresent(loginResponse::setEmail);

        // JSON 응답 반환
        return ResponseEntity.ok(loginResponse);
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
    public String getMemberJoin(@ModelAttribute("loginRegForm") LoginRegForm loginRegForm, @RequestParam(name = "s", required = false) Boolean status) {
        return "view/member/join";
    }

    @PostMapping("/join")
    public String postMemberJoin(@Validated @ModelAttribute("loginRegForm") LoginRegForm tmpForm
                                 , BindingResult bindingResult , @RequestParam("email") String email
                                , RedirectAttributes redirectAttributes,
                                 HttpServletResponse response) throws MessagingException {

        //공백 검사
        if(bindingResult.hasFieldErrors("email")){
            log.info("errors: {}", bindingResult.getAllErrors());
            return "view/member/join";
        }

        tmpForm.setEmail(email);

        //이메일 형식 검증
        loginRegValidator.validate(tmpForm, bindingResult);
        if(bindingResult.hasFieldErrors("email")){
            log.info("errors: {}", bindingResult.getAllErrors());
            return "view/member/join";
        }

        log.info("올바른 이메일");
        //이메일을 쿠키에 담아 브라우저에 전송한다. 이 쿠키는 /member/join-input에서 쓰인다.
        emailService.createEmailCookie(response, email);
        emailService.sendEmail(email);
        log.info("이메일이 전송되었습니다.");
        redirectAttributes.addFlashAttribute("loginRegForm", tmpForm);
        redirectAttributes.addAttribute("s",true);
        return "redirect:/member/join";
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
