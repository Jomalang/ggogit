package io.ggogit.ggogit.api.member.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ggogit.ggogit.api.member.dto.AuthInfoResponse;
import io.ggogit.ggogit.domain.member.api.dto.AuthResponseDto;
import io.ggogit.ggogit.domain.member.api.dto.KakaoAccessResponseDto;
import io.ggogit.ggogit.domain.member.api.dto.KakaoUserInfo;
import io.ggogit.ggogit.domain.member.api.dto.NaverOauthAccessDto;
import io.ggogit.ggogit.domain.member.api.oauth2.GoogleOauthClient;
import io.ggogit.ggogit.domain.member.api.oauth2.KakaoOauthClient;
import io.ggogit.ggogit.domain.member.api.oauth2.NaverOauthClient;
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
    private final NaverOauthClient naverOauthClient;
    private final KakaoOauthClient kakaoOauthClient;

    @PostMapping("/oauthGoogle")
    public ResponseEntity<AuthInfoResponse> oauthGoogle(
        @RequestBody String accessToken
    ){
        int index = accessToken.length();
        AuthResponseDto authResponseDto = googleOauthClient.fetchToken(accessToken.substring(10, index-2));

        try {
            Member member = memberService.getByEmail(authResponseDto.getEmail());
            String newAccessToken = memberService.generateAccessToken(member);
            AuthInfoResponse response =
                    AuthInfoResponse.of(member.getId(), member.getEmail(), member.getUsername(), member.getMemberProfileImage().getName(),member.getNickname(), member.getRole(), newAccessToken);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            AuthInfoResponse response = AuthInfoResponse.of(authResponseDto.getEmail(), authResponseDto.getName(), authResponseDto.getPicture(),null);
            System.out.println(response);
            return new ResponseEntity<>(response,  HttpStatus.OK);
        }
    }
    @PostMapping("/oauthNaver")
    public ResponseEntity<AuthInfoResponse> oauthNaver(
            @RequestBody String receiveDto
    ){
        ObjectMapper mapper = new ObjectMapper();
        AuthResponseDto authResponseDto;
        NaverOauthAccessDto naverOauthAccessDto;
        try {
            ServingDto servingDto = mapper.readValue(receiveDto, ServingDto.class);
            naverOauthAccessDto = naverOauthClient.getAccessToken(servingDto.code, servingDto.state);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(naverOauthAccessDto == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        authResponseDto = naverOauthClient.findMemberInfo(naverOauthAccessDto);
        try {
            Member member = memberService.getByEmail(authResponseDto.getEmail());
            String newAccessToken = memberService.generateAccessToken(member);
            AuthInfoResponse response =
                    AuthInfoResponse.of(member.getId(), member.getEmail(), member.getUsername(), member.getMemberProfileImage().getName(),member.getNickname(), member.getRole(), newAccessToken);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            AuthInfoResponse response = AuthInfoResponse.of(authResponseDto.getEmail(), authResponseDto.getName(), authResponseDto.getPicture(),null);
            return new ResponseEntity<>(response,  HttpStatus.OK);
        }
    }


    @PostMapping("/oauthKakao")
    public ResponseEntity<AuthInfoResponse> oauthKKakao(
            @RequestBody String receiveDto
    ) {
        ObjectMapper mapper = new ObjectMapper();
        AuthResponseDto authResponseDto;
        KakaoAccessResponseDto kakaoAccessResponseDto;
        String decodedString;
        try {
            ServingDto servingDto = mapper.readValue(receiveDto, ServingDto.class);
            kakaoAccessResponseDto = kakaoOauthClient.getAccessToken(servingDto.code);

            String[] base64EncodedString = kakaoAccessResponseDto.getIdToken().split("\\.");
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64EncodedString[1]);
            decodedString = new String(decodedBytes);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        KakaoUserInfo kakaoUserInfo = null;
        try {
            kakaoUserInfo = mapper.readValue(decodedString, KakaoUserInfo.class);
        } catch (JsonProcessingException e) {
            //실패 메세지 출력
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            Member member = memberService.getByEmail(kakaoUserInfo.getEmail());
            String newAccessToken = memberService.generateAccessToken(member);
            AuthInfoResponse response =
                    AuthInfoResponse.of(member.getId(), member.getEmail(), member.getUsername(), member.getMemberProfileImage().getName(),member.getNickname(), member.getRole(), newAccessToken);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            AuthInfoResponse response = AuthInfoResponse.of(kakaoUserInfo.getEmail(), kakaoUserInfo.getNickname(), kakaoUserInfo.getPicture(),null);
            return new ResponseEntity<>(response,  HttpStatus.OK);
        }
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class ServingDto{
    @JsonProperty("code")
    String code;
    @JsonProperty("state")
    String state;
}
