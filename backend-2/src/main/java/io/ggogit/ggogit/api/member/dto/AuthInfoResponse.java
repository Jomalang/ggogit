package io.ggogit.ggogit.api.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.ggogit.ggogit.domain.member.entity.RoleType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthInfoResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("picture")
    private String picture;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("role")
    private RoleType role;
    @JsonProperty("accessToken")
    private String accessToken;

    public static AuthInfoResponse of(Long id,String email, String name, String picture, String nickname, RoleType role, String accessToken) {
        AuthInfoResponse authInfoResponse = new AuthInfoResponse();
        authInfoResponse.id = id;
        authInfoResponse.email = email;
        authInfoResponse.name = name;
        authInfoResponse.picture = picture;
        authInfoResponse.nickname = nickname;
        authInfoResponse.role = role;
        authInfoResponse.accessToken = accessToken;
        return authInfoResponse;
    }

    public static AuthInfoResponse of(String email, String name, String picture, String accessToken) {
        return of(null, email, name, picture, null, null, accessToken);
    }
}
