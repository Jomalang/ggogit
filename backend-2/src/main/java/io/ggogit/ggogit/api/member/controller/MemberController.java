package io.ggogit.ggogit.api.member.controller;

import io.ggogit.ggogit.api.member.dto.*;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.MemberService;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    ) {
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
        return new ResponseEntity<>(response, HttpStatus.OK);
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

    // 비밀번호 변경 이메일 전송
    @PostMapping("/password-reset/send-email")
    public ResponseEntity<MemberPasswordResetSendEmailResponse> passwordResetSendEmail(
            @RequestBody MemberPasswordResetSendEmailRequest dto
    ) {

        // 사용자 확인
        if (!memberService.existsEmail(dto.getEmail())) {
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
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<Member> findByNickname(@PathVariable String nickname) {
        Optional<Member> member = memberService.findByNickname(nickname);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 사용자 이름으로 회원 조회
    @GetMapping("/username/{username}")
    public ResponseEntity<Member> findByUsername(@PathVariable String username) {
        Optional<Member> member = memberService.findByUsername(username);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 트리로 회원 조회
    @PostMapping("/trees")
    public ResponseEntity<Member> findByTrees(@RequestBody List<Tree> trees) {
        Optional<Member> member = memberService.findByTrees(trees);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}