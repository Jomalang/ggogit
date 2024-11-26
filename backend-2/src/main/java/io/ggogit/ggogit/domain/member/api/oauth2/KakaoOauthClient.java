package io.ggogit.ggogit.domain.member.api.oauth2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ggogit.ggogit.domain.member.api.dto.AuthResponseDto;
import io.ggogit.ggogit.domain.member.api.dto.KakaoAccessResponseDto;
import io.ggogit.ggogit.domain.member.api.dto.NaverOauthAccessDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoOauthClient {

    private final String KAKAO_OAUTH_ACCESS_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_OAUTH_PROFILE_URL = "https://kapi.kakao.com/v2/user/me";

    @Value("${kakao.api.client_id}")
    private String clientId;

    public KakaoAccessResponseDto getAccessToken(String code) {
        String url = KAKAO_OAUTH_ACCESS_URL;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // Content-Type 설정

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", "http://ggogit.taecobug.io:3006/member/login");
        body.add("code", code);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.postForObject(url, request, String.class);
            ObjectMapper mapper = new ObjectMapper();
            KakaoAccessResponseDto kakaoAccessResponseDto = mapper.readValue(response, KakaoAccessResponseDto.class);
            return kakaoAccessResponseDto;
        } catch (RestClientException e) {
            System.err.println("네이버 OAuth 요청 중 오류 발생: " + e.getMessage());
            return null;
        } catch (JsonProcessingException e) {
            System.err.println("JSON 파싱 중 오류 발생: " + e.getMessage());
            return null;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class NaverResponseDto{
        @JsonProperty("resultcode")
        String resultcode;
        @JsonProperty("message")
        String message;
        @JsonProperty("response")
        NaverUserInfo response;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class NaverUserInfo {
        @JsonProperty("id")
        private String id;
        @JsonProperty("email")
        private String email;
        @JsonProperty("name")
        private String name;
        @JsonProperty("nickname")
        private String nickname;
        @JsonProperty("profile_image")
        private String profile_image;
    }


}
