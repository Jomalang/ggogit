package io.ggogit.ggogit.domain.member.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoAccessResponseDto{
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("id_token")
    private String idToken;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("expires_in")
    private String expiresIn;
    @JsonProperty("refresh_token_expires_in")
    private String refreshTokenExpiresIn;
}
