package io.ggogit.ggogit.domain.member.api.oauth2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ggogit.ggogit.domain.member.api.dto.AuthResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.*;
import io.ggogit.ggogit.domain.member.api.dto.NaverOauthAccessDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
@RequiredArgsConstructor
public class NaverOauthClient {

    private final String NAVER_OAUTH_ACCESS_URL = "https://nid.naver.com/oauth2.0/token";
    private final String NAVER_OAUTH_PROFILE_URL = "https://openapi.naver.com/v1/nid/me";

    @Value("${naver.api.client_id}")
    private String clientId;
    @Value("${naver.api.client_secret}")
    private String clientSecret;

    public NaverOauthAccessDto getAccessToken(String code, String state) {
        String url = NAVER_OAUTH_ACCESS_URL;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // Content-Type 설정

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        body.add("state", state);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.postForObject(url, request, String.class);
            ObjectMapper mapper = new ObjectMapper();
            NaverOauthAccessDto naverOauthAccessDto = mapper.readValue(response, NaverOauthAccessDto.class);
            return naverOauthAccessDto;
        } catch (RestClientException e) {
            System.err.println("네이버 OAuth 요청 중 오류 발생: " + e.getMessage());
            return null;
        } catch (JsonProcessingException e) {
            System.err.println("JSON 파싱 중 오류 발생: " + e.getMessage());
            return null;
        }
    }

    public AuthResponseDto findMemberInfo(NaverOauthAccessDto naverOauthAccessDto) {
        String url = NAVER_OAUTH_PROFILE_URL; // 프로필 조회 URL
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + naverOauthAccessDto.getAccessToken()); // 액세스 토큰 설정

        // HttpEntity 객체 생성 (헤더 포함)
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        try {
            // 네이버 API 호출 (프로필 정보 조회)
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            System.out.println(response.getBody());
            // 응답 본문 출력
            if(response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("네이버 프로필 정보 조회 중 오류 발생: " + response.getStatusCode());
            }
            ObjectMapper mapper = new ObjectMapper();
            NaverResponseDto authResponseDto = mapper.readValue(response.getBody(), NaverResponseDto.class);
            NaverUserInfo naverUserInfo = authResponseDto.getResponse();
            return AuthResponseDto.of(naverUserInfo.getEmail(), naverUserInfo.getName(), naverUserInfo.getProfile_image(), naverUserInfo.getNickname());

        } catch (RestClientException e) {
            throw new RuntimeException("네이버 프로필 정보 조회 중 오류 발생: " + e.getMessage(), e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
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
