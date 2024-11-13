package io.ggogit.ggogit.api.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthInfoResponse {
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("picture")
    private String picture;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("accessToken")
    private String accessToken;

    public static AuthInfoResponse of(String email, String name, String picture, String nickname, String accessToken) {
        AuthInfoResponse authInfoResponse = new AuthInfoResponse();
        authInfoResponse.email = email;
        authInfoResponse.name = name;
        authInfoResponse.picture = picture;
        authInfoResponse.nickname = nickname;
        authInfoResponse.accessToken = accessToken;
        return authInfoResponse;
    }

    public static AuthInfoResponse of(String email, String name, String picture, String accessToken) {
        return of(email, name, picture, null, accessToken);
    }
}
