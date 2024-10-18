package io.ggogit.ggogit.api.member.controller;

import io.ggogit.ggogit.api.member.dto.LoginFormResponse;
import io.ggogit.ggogit.api.member.form.LoginForm;
import io.ggogit.ggogit.api.member.form.LoginRegForm;
import io.ggogit.ggogit.api.member.session.SessionConst;
import io.ggogit.ggogit.api.member.validation.LoginRegValidator;
import io.ggogit.ggogit.api.member.validation.LoginValidator;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.EmailService;
import io.ggogit.ggogit.domain.member.service.LoginService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@SessionAttributes("loginMember")
@Slf4j
public class MemberController {

    private final LoginService loginService;
    private final EmailService emailService;
    private final LoginRegValidator loginRegValidator;
    private final LoginValidator loginValidator;

    @GetMapping("/login")
    public ResponseEntity<?> getMemberLogin(@RequestParam(value = "j", required = false) boolean isNewMember) {
        LoginRegForm loginRegForm = new LoginRegForm();
        return ResponseEntity.ok(new LoginFormResponse(loginRegForm, isNewMember));
    }

    @PostMapping("/login")
    public ResponseEntity<?> postMemberLogin(@Validated @RequestBody LoginForm loginForm,
                                             BindingResult bindingResult,
                                             HttpServletRequest request,
                                             @RequestParam(value = "redirectURL", required = false) String redirectURL) {

        if (bindingResult.hasErrors()) {
            log.info("Validation errors: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        if (loginValidator.supports(loginForm.getClass())) {
            loginValidator.validate(loginForm, bindingResult);
            if (bindingResult.hasErrors()) {
                log.info("Custom validation errors: {}", bindingResult.getAllErrors());
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }
        }

        Member member = loginForm.toMember();
        Member loginMember = loginService.login(member).getBody();

        // Create session upon successful login
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return ResponseEntity.ok().body("Login successful. Redirect to: " + (redirectURL != null ? redirectURL : "/home/" + loginMember.getNickname()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> postMemberLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @GetMapping("/join-input")
    public ResponseEntity<LoginRegForm> getMemberJoinInput(HttpServletRequest request) {
        LoginRegForm loginRegForm = new LoginRegForm();
        Optional<String> emailCookie = emailService.getEmailCookie(request);
        emailCookie.ifPresent(loginRegForm::setEmail);
        return ResponseEntity.ok(loginRegForm);
    }

    @PostMapping("/join-input")
    public ResponseEntity<?> postMemberJoinInput(@Validated @RequestBody LoginRegForm loginRegForm,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("Validation errors: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        if (loginRegValidator.supports(loginRegForm.getClass())) {
            loginRegValidator.validate(loginRegForm, bindingResult);
            if (bindingResult.hasErrors()) {
                log.info("Custom validation errors: {}", bindingResult.getAllErrors());
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }
        }

        Member newMember = loginRegForm.toMember();
        loginService.RegMember(newMember);

        return ResponseEntity.ok("성공적으로 등록되었습니다.");
    }

    @PostMapping("/join")
    public ResponseEntity<?> postMemberJoin(@Validated @RequestBody LoginRegForm tmpForm,
                                            BindingResult bindingResult,
                                            HttpServletResponse response) throws MessagingException {
        if (bindingResult.hasFieldErrors("email")) {
            log.info("Validation errors: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        loginRegValidator.validate(tmpForm, bindingResult);
        if (bindingResult.hasFieldErrors("email")) {
            log.info("Email validation errors: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        emailService.createEmailCookie((Model) response, tmpForm.getEmail());
        emailService.sendEmail(tmpForm.getEmail());
        log.info("이메일이 성공적으로 전송되었습니다.");

        return ResponseEntity.ok("등록된 이메일이 성공적으로 전송되었습니다.");
    }

    @GetMapping("/pw/rst")
    public ResponseEntity<String> getMemberPwRst() {
        // 비밀번호 초기화 페이지 정보가 필요한 경우 처리
        return ResponseEntity.ok("비밀번호 재설정 페이지가 성공적으로 로드되었습니다.");
    }

    @PostMapping("/pw/rst")
    public ResponseEntity<String> postMemberPwRst() {
        // 비밀번호 초기화 로직 처리 후 응답 반환
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("비밀번호 재설정 요청이 수신되었습니다.");
    }
}
