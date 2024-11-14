package io.ggogit.ggogit.api.member.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ggogit.ggogit.api.member.dto.AuthInfoResponse;
import io.ggogit.ggogit.domain.member.api.dto.AuthResponseDto;
import io.ggogit.ggogit.domain.member.api.oauth2.GoogleOauthClient;
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
            @RequestBody String accessToken
    ){
        ObjectMapper mapper = new ObjectMapper();
        try {
            ServingDto servingDto = mapper.readValue(accessToken, ServingDto.class);
            AuthResponseDto authResponseDto = naverOauthClient.getAccessToken(servingDto.token, servingDto.state);

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}

@JsonInclude(JsonInclude.Include.NON_NULL)
class ServingDto{
    @JsonProperty("token")
    String token;
    @JsonProperty("state")
    String state;
}
