package io.ggogit.ggogit.api.member.controller;

import io.ggogit.ggogit.api.member.dto.*;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.EmailService;
import io.ggogit.ggogit.domain.member.service.LoginService;
import io.ggogit.ggogit.api.member.session.SessionConst;
import io.ggogit.ggogit.api.member.validation.LoginRegValidator;
import io.ggogit.ggogit.api.member.validation.LoginValidator;
import io.ggogit.ggogit.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final LoginService loginService;
    private final EmailService emailService;
    private final LoginRegValidator loginRegValidator;
    private final LoginValidator loginValidator;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> postMemberLogin(@Validated @RequestBody MemberLoginRequestDto loginForm,
                                                               BindingResult bindingResult,
                                                               HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();

        // 공백 검사
        if (bindingResult.hasErrors()) {
            log.info("errors: {}", bindingResult.getAllErrors());
            response.put("error", "입력값에 오류가 있습니다.");
            return ResponseEntity.badRequest().body(response);
        }

        // 유효성 검사
        if (loginValidator.supports(loginForm.getClass())) {
            loginValidator.validate(loginForm, bindingResult);
            if (bindingResult.hasErrors()) {
                log.info("errors: {}", bindingResult.getAllErrors());
                response.put("error", "아이디 또는 비밀번호가 맞지 않습니다.");
                return ResponseEntity.badRequest().body(response);
            }
        }

        MemberLoginRequestDto loginRequestDto = new MemberLoginRequestDto();
        loginRequestDto.setEmail(loginForm.getEmail());
        loginRequestDto.setPassword(loginForm.getPassword());
        Member loginMember = loginService.login(loginRequestDto);

        // 로그인 성공
        // 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        response.put("member", new MemberLoginResponseDto(loginMember));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> postMemberLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @PostMapping("/join-input")
    public ResponseEntity<Map<String, Object>> postMemberJoinInput(@Validated @RequestBody MemberRegRequestDto loginRegForm,
                                                                   BindingResult bindingResult) {

        Map<String, Object> response = new HashMap<>();

        // 공백 검사
        if (bindingResult.hasErrors()) {
            log.info("errors: {}", bindingResult.getAllErrors());
            response.put("error", "입력값에 오류가 있습니다.");
            return ResponseEntity.badRequest().body(response);
        }

        // 유효성 검사
        if (loginRegValidator.supports(loginRegForm.getClass())) {
            loginRegValidator.validate(loginRegForm, bindingResult);
            if (bindingResult.hasErrors()) {
                log.info("errors: {}", bindingResult.getAllErrors());
                response.put("error", "입력값에 오류가 있습니다.");
                return ResponseEntity.badRequest().body(response);
            }
        }

        // 회원 가입 성공
        MemberRegRequestDto regRequestDto = new MemberRegRequestDto();
        regRequestDto.setEmail(loginRegForm.getEmail());
        regRequestDto.setPassword(loginRegForm.getPassword());
        regRequestDto.setNickname(loginRegForm.getNickname());
        regRequestDto.setUsername(loginRegForm.getUsername());
        regRequestDto.setIntroduction(loginRegForm.getIntroduction());
        Member newMember = loginService.regMember(regRequestDto);
        response.put("member", new MemberRegResponseDto(newMember));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> postMemberJoin(@Validated @RequestBody MemberRegRequestDto tmpForm,
                                                              BindingResult bindingResult) {

        Map<String, Object> responseBody = new HashMap<>();

        // 공백 검사
        if (bindingResult.hasFieldErrors("email")) {
            log.info("errors: {}", bindingResult.getAllErrors());
            responseBody.put("error", "이메일을 입력해주세요.");
            return ResponseEntity.badRequest().body(responseBody);
        }

        // 이메일 형식 검증
        loginRegValidator.validate(tmpForm, bindingResult);
        if (bindingResult.hasFieldErrors("email")) {
            log.info("errors: {}", bindingResult.getAllErrors());
            responseBody.put("error", "이메일 형식이 올바르지 않습니다.");
            return ResponseEntity.badRequest().body(responseBody);
        }

        log.info("올바른 이메일");
        try {
            emailService.sendEmail(tmpForm.getEmail());
        } catch (Exception e) {
            responseBody.put("error", "이메일 전송에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
        log.info("이메일이 전송되었습니다.");
        responseBody.put("message", "이메일이 전송되었습니다.");
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/pw/rst")
    public ResponseEntity<String> getMemberPwRst() {
        return ResponseEntity.ok("비밀번호 재설정 페이지입니다.");
    }

    @PostMapping("/pw/rst")
    public ResponseEntity<String> postMemberPwRst() {
        return ResponseEntity.ok("비밀번호 재설정 요청이 완료되었습니다.");
    }

    // 기존이미지뷰 반환 로직
    @GetMapping("/image/{memberId}")
    public ResponseEntity<MemberImageDto> getMemberImage(@PathVariable Long memberId) {
        MemberImageDto memberImageDto = memberService.getMemberImageDto(memberId);
        return ResponseEntity.ok(memberImageDto);
    }
}