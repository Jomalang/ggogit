package io.ggogit.ggogit.api.member.controller;

import io.ggogit.ggogit.api.member.dto.IsMemberRequest;
import io.ggogit.ggogit.api.member.dto.MemberLoginRequest;
import io.ggogit.ggogit.api.member.dto.MemberLoginResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.MemberService;
import io.jsonwebtoken.io.Decoders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Value("${jwt.access-expiration}")
    private long accessExpirationTime;

    private final MemberService memberService;

    @PostMapping("/isMember")
    public ResponseEntity<Boolean> login(
        @RequestBody IsMemberRequest isMemberRequest
    ) {
        Boolean isMember = memberService.existsEmail(isMemberRequest.getEmail());
        return new ResponseEntity<>(isMember, HttpStatus.OK);
    }

    // JWT 토큰 신규 발급
    @PostMapping("/newToken")
    public ResponseEntity<MemberLoginResponse> newToken(
            @RequestBody IsMemberRequest isMemberRequest
    ) {

        Member member = memberService.getByEmail(isMemberRequest.getEmail());

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
        MemberLoginResponse response = MemberLoginResponse.of(accessToken, refreshToken, accessExpirationTime, "New Token");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
