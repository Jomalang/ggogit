package io.ggogit.ggogit.domain.member.api.oauth2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ggogit.ggogit.domain.member.api.dto.AuthResponseDto;
import io.ggogit.ggogit.domain.member.api.dto.GoogleOauthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleOauthClient {

    private final String GOOGLE_OAUTH_URL = "https://www.googleapis.com/oauth2/v3/userinfo?access_token=";

    public AuthResponseDto fetchToken(String token) {
        String url = GOOGLE_OAUTH_URL + token;
        RestTemplate restTemplate = new RestTemplate();
        String userInfo = restTemplate.getForObject(url, String.class);

        System.out.println(userInfo);
        if (userInfo == null) {
            return null;
        }

        // Jackson ObjectMapper를 사용하여 JSON 문자열을 객체로 변환
        ObjectMapper mapper = new ObjectMapper();
        try {
            GoogleOauthDto googleOauthDto = mapper.readValue(userInfo, GoogleOauthDto.class);
            System.out.println(googleOauthDto);
            return new AuthResponseDto(googleOauthDto.getEmail(), googleOauthDto.getName(), googleOauthDto.getPicture(), googleOauthDto.getNickname());

        } catch (JsonProcessingException e) {
            log.error("JSON 처리 중 오류 발생", e);
            return null;
        }
    }

}
