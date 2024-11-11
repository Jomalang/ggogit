package io.ggogit.ggogit.api.member.controller;

import io.ggogit.ggogit.api.member.dto.*;
import io.ggogit.ggogit.domain.member.entity.EmailJoinToken;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.entity.PassWordRest;
import io.ggogit.ggogit.domain.member.service.MemberService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    @Value("${jwt.access-expiration}")
    private long accessExpirationTime;

    private final MemberService memberService;

    // 회원가입 이메일 전송
    @PostMapping("/join/send-email")
    public ResponseEntity<MemberSendEmailResponse> joinSendEmail(
            @RequestBody MemberSendEmailRequest dto
    ) throws MessagingException {
        // 기존 회원 확인
        if (memberService.existsEmail(dto.getEmail())) {
            MemberSendEmailResponse response = MemberSendEmailResponse.of(false, "이미 가입된 이메일입니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 기존 템프 정보 삭제
        memberService.deleteJoinTmpEmailInfo(dto.getEmail());

        // 이메일 전송
        memberService.joinSendEmail(dto.getEmail());
        MemberSendEmailResponse response = MemberSendEmailResponse.of("이메일 전송 완료");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 이메일 인증 확인된 이메일 조회
    @PostMapping("/join/check-email")
    public ResponseEntity<MemberCheckEmailResponse> checkEmail(
            @Valid @RequestBody MemberCheckEmailRequest dto
    ) {
        EmailJoinToken emailJoinToken = memberService.findEmailJoinToken(dto.getKey());
        MemberCheckEmailResponse response = MemberCheckEmailResponse.of(emailJoinToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/password/check-email")
    public ResponseEntity<MemberCheckEmailResponse> passwordCheckEmail(
            @Valid @RequestBody MemberCheckEmailRequest dto
    ) {
        PassWordRest passWordRest = memberService.findPassWordRest(dto.getKey());
        MemberCheckEmailResponse response = MemberCheckEmailResponse.of(passWordRest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 회원 가입
    @PostMapping("/join")
    public ResponseEntity<MemberJoinResponse> join(
            @RequestBody MemberJoinRequest dto
    ) {
        // 이메일 인증 확인
        if (!memberService.existsEmailJoinToken(dto.getEmail(), dto.getJoinToken())) {
            MemberJoinResponse response = MemberJoinResponse.of(false, "이메일 인증에 실패했습니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 회원 가입
        Member member = dto.toMember();
        memberService.join(member);
        MemberJoinResponse response = MemberJoinResponse.of("회원 가입 완료");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // JWT 토큰 신규 발급
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(
            @RequestBody MemberLoginRequest dto
    ) {
        // 로그인 로직
        Member member = dto.toMember();

        if (!memberService.loginCheck(member)) {
            MemberLoginResponse response = MemberLoginResponse.of("로그인 실패");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String accessToken = memberService.generateAccessToken(member);
        String refreshToken = memberService.generateRefreshToken(member);

        // httpOnly 쿠키 설정
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false)  // HTTPS에서만 전송
                .path("/")   // 적용 경로 설정
                .maxAge(7 * 24 * 60 * 60) // 7일 동안 유효
                    .sameSite("Strict") // 다른 관련 옵션 설정 (Strict, Lax, None)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        MemberLoginResponse response = MemberLoginResponse.of(accessToken, accessExpirationTime, "로그인 성공");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    // JWT 토큰 재발급
    @PostMapping("/refresh")
    public ResponseEntity<MemberRefreshResponse> refresh(
            @CookieValue("refreshToken") String refreshToken
    ) {
        if (!memberService.validateToken(refreshToken)) {
            MemberRefreshResponse response = MemberRefreshResponse.of("토큰이 유효하지 않습니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        MemberRefreshResponse response = memberService.refresh(refreshToken);
        response.setMessage("토큰 재발급 완료");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 회원 정보 수정
    @PostMapping("/{memberId}/edit")
    public ResponseEntity<MemberEditResponse> edit(
            @PathVariable Long memberId,
            @RequestHeader("Authorization") String accessToken, // AOP로 처리
            @RequestBody MemberEditRequest dto
    ) {
        // 회원 정보 수정
        Member member = dto.toMember();
        memberService.edit(memberId, member);
        MemberEditResponse response = MemberEditResponse.of("회원 정보 수정 완료");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 계정 찾기 이메일 전송
    @PostMapping("/find/send-email")
    public ResponseEntity<MemberPasswordResetSendEmailResponse> findSendEmail(
            @RequestBody MemberPasswordResetSendEmailRequest dto
    ) throws MessagingException {

        // 사용자 확인
        if (!memberService.existsEmail(dto.getEmail(), dto.getUsername())) {
            MemberPasswordResetSendEmailResponse response = MemberPasswordResetSendEmailResponse.of("가입되지 않은 이메일입니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 이메일 전송
        memberService.passwordResetSendEmail(dto.getEmail());

        // 비밀번호 변경
        MemberPasswordResetSendEmailResponse response = MemberPasswordResetSendEmailResponse.of("비밀번호 변경 이메일 전송 완료");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 비밀번호 변경
    @PostMapping("/password-reset")
    public ResponseEntity<MemberPasswordResetResponse> passwordReset(
            @RequestBody MemberPasswordResetRequest dto
    ) {
        // 비밀번호 변경
        String password = dto.getNewPassword();
        String token = dto.getToken();
        memberService.passwordReset(password, token);
        MemberPasswordResetResponse response = MemberPasswordResetResponse.of("비밀번호 변경 완료");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 닉네임으로 회원 조회
    @PostMapping("/nickname")
    public ResponseEntity<MemberResponse> findByNickname(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.findByNickname(memberRequest.getNickname());
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    // 사용자 이름으로 회원 조회
    @PostMapping("/username")
    public ResponseEntity<MemberResponse> findByUsername(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.findByUsername(memberRequest.getUsername());
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    // 이메일로 회원 조회
    @PostMapping("/email")
    public ResponseEntity<MemberResponse> findByEmail(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.findByEmail(memberRequest.getEmail());
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

}