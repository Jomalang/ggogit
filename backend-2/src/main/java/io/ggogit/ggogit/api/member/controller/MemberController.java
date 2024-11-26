package io.ggogit.ggogit.api.member.controller;

import io.ggogit.ggogit.api.member.dto.*;
import io.ggogit.ggogit.domain.image.service.ImageService;
import io.ggogit.ggogit.domain.member.entity.EmailJoinToken;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.entity.PassWordRest;
import io.ggogit.ggogit.domain.member.security.CustomUserDetails;
import io.ggogit.ggogit.domain.member.service.MemberImageService;
import io.ggogit.ggogit.domain.member.service.MemberService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;


@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    @Value("${jwt.access-expiration}")
    private long accessExpirationTime;

    private final MemberService memberService;
    private final MemberImageService memberImageService;

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

    // 가입 절차 여부 증명
    @PostMapping("/join/check-token")
    public ResponseEntity<MemberCheckTokenResponse> checkToken(
            @Valid @RequestBody MemberCheckTokenRequest dto
    ) {
        boolean result = memberService.existsEmailJoinToken(dto.getKey());
        MemberCheckTokenResponse response = MemberCheckTokenResponse.of(result);
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
    public ResponseEntity<MemberLoginResponse> join(
            @RequestBody MemberJoinRequest dto
    ) {
        // 이메일 인증 확인
        if (!memberService.existsEmailJoinToken(dto.getEmail(), dto.getJoinToken())) {
            MemberLoginResponse response = MemberLoginResponse.of("이메일 인증에 실패했습니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 회원 가입
        Member member = dto.toMember();
        Member savedMember = memberService.join(member);
        String accessToken = memberService.generateAccessToken(savedMember);
        HttpHeaders headers = new HttpHeaders();
        MemberLoginResponse response = MemberLoginResponse.of(accessToken, accessExpirationTime, "로그인 성공");
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    // OAuth 회원 가입
    @PostMapping("/oauth/join")
    public ResponseEntity<MemberLoginResponse> oauthJoin(
            @RequestBody MemberOAuthJoinRequest dto
    ) {
        // 회원 가입
        Member member = dto.toMember();
        String profileImage = dto.getPicture();

        if (memberService.existsEmail(member.getEmail())) {
            MemberLoginResponse response = MemberLoginResponse.of("이미 가입된 이메일입니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Member newMember = memberService.join(member, profileImage);
        String accessToken = memberService.generateAccessToken(newMember);
        HttpHeaders headers = new HttpHeaders();
        MemberLoginResponse response = MemberLoginResponse.of(accessToken, accessExpirationTime, "로그인 성공");
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
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

        Member loginMember = memberService.getByEmail(member.getEmail());
        String accessToken = memberService.generateAccessToken(loginMember);
        HttpHeaders headers = new HttpHeaders();
        MemberLoginResponse response = MemberLoginResponse.of(accessToken, accessExpirationTime, "로그인 성공");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    // JWT 토큰 재발급
    @PostMapping("/refresh")
    @CrossOrigin(origins = "http://ggogit.taecobug.io:3006", allowCredentials = "true")
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
    //TODO: 서비스간의 트랜잭션 전파 처리 필요
    @PostMapping("/{memberId}/edit")
    public ResponseEntity<MemberEditResponse> edit(
            @PathVariable Long memberId,
            @ModelAttribute MemberEditRequest dto,
            @RequestParam(value = "memberProfileImage", required = false) MultipartFile memberProfileImage,
            @RequestParam(value = "memberBackgroundImage", required = false) MultipartFile memberBackgroundImage
            ) {

        // 회원 정보 수정
        Member memberDto = dto.toMember();
        memberService.edit(memberId, memberDto);
        MemberEditResponse response = MemberEditResponse.of("회원 정보 수정 완료");

        // 회원 프로필 이미지 수정
        if (memberProfileImage != null) {
            memberImageService.uploadProfile(memberId, memberProfileImage);
        }

        // 회원 배경 이미지 수정
        if (memberBackgroundImage != null) {
            memberImageService.uploadBackground(memberId, memberBackgroundImage);
        }

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
    @GetMapping("/nickname")
    public ResponseEntity<MemberResponse> findByNickname(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.findByNickname(memberRequest.getNickname());
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    // 사용자 이름으로 회원 조회
    @GetMapping("/username")
    public ResponseEntity<MemberResponse> findByUsername(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.findByUsername(memberRequest.getUsername());
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    // 이메일로 회원 조회
    @GetMapping("/email")
    public ResponseEntity<MemberResponse> findByEmail(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.findByEmail(memberRequest.getEmail());
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }

    // Id로 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(
            @PathVariable(name="id") Long memberId) {
        Member member = memberService.findById(memberId);
        MemberResponse memberResponse = MemberResponse.of(member);
        return new ResponseEntity<>(memberResponse, HttpStatus.OK);
    }
}