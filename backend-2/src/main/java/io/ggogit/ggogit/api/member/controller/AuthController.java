package io.ggogit.ggogit.api.member.controller;

import io.ggogit.ggogit.api.member.dto.AuthInfoResponse;
import io.ggogit.ggogit.domain.member.api.dto.AuthResponseDto;
import io.ggogit.ggogit.domain.member.api.dto.GoogleOauthDto;
import io.ggogit.ggogit.domain.member.api.oauth2.GoogleOauthClient;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/auth", produces = "application/json")
@RequiredArgsConstructor
public class AuthController {

    @Value("${jwt.access-expiration}")
    private long accessExpirationTime;

    private final MemberService memberService;
    private final GoogleOauthClient googleOauthClient;

    @PostMapping("/authentication")
    public ResponseEntity<AuthInfoResponse> authentication(
        @RequestBody String accessToken
    ){
        int index = accessToken.length();
        AuthResponseDto authResponseDto = googleOauthClient.fetchToken(accessToken.substring(10, index-2));

        try {
            Member member = memberService.getByEmail(authResponseDto.getEmail());
            String newAccessToken = memberService.generateAccessToken(member);
            AuthInfoResponse response = AuthInfoResponse.of(member.getEmail(), member.getUsername(), member.getMemberProfileImage().getName(), newAccessToken);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {

            AuthInfoResponse response = AuthInfoResponse.of(authResponseDto.getEmail(), authResponseDto.getName(), authResponseDto.getPicture(),null);
            System.out.println(response);
            return new ResponseEntity<AuthInfoResponse>(response,  HttpStatus.OK);
        }
    }

}
