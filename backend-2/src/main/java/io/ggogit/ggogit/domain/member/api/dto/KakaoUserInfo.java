package io.ggogit.ggogit.domain.member.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoUserInfo {
    @JsonProperty("aud")
    private String aud;
    @JsonProperty("sub")
    private String sub;
    @JsonProperty("auth_time")
    private String authTime;
    @JsonProperty("iss")
    private String iss;
    @JsonProperty("email")
    private String email;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("exp")
    private String exp;
    @JsonProperty("iat")
    private String iat;
    @JsonProperty("picture")
    private String picture;
}
