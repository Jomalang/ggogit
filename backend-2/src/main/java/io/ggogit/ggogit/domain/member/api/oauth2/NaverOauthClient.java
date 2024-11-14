package io.ggogit.ggogit.domain.member.api.oauth2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ggogit.ggogit.domain.member.api.dto.AuthResponseDto;
import org.springframework.http.HttpHeaders;
import io.ggogit.ggogit.domain.member.api.dto.GoogleOauthDto;
import io.ggogit.ggogit.domain.member.api.dto.NaverOauthAccessDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
@RequiredArgsConstructor
public class NaverOauthClient {

    private final String NAVER_OAUTH_URL = "https://nid.naver.com/oauth2.0/token";

    @Value("${naver.api.client_id}")
    private String clientId;
    @Value("${naver.api.client_secret}")
    private String clientSecret;

    public AuthResponseDto getAccessToken(String token, String state) {
        String url = NAVER_OAUTH_URL;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // Content-Type 설정

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", token);
        body.add("state", state);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String>  response = restTemplate.postForEntity(url, request, String.class);
            System.out.println(response);
            return null;

//            if (response.getBody() != null) {
//
//
//
//                // 오류가 있는 경우 처리
//                if (responseBody.getError() != null) {
//                    System.err.println("OAuth Error: " + responseBody.getErrorDescription());
//                    return null; // 또는 적절한 오류 처리 로직 추가
//                }
//
//                return null;
//            } else {
//                System.err.println("응답 본문이 비어 있습니다.");
//                return null;
//            }

        } catch (RestClientException e) {
            System.err.println("네이버 OAuth 요청 중 오류 발생: " + e.getMessage());
            return null;
        }
    }
}
