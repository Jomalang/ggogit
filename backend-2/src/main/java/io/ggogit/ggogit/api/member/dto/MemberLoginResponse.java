package io.ggogit.ggogit.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginResponse {
    private String accessToken;
    private Long expiresIn;
    private String message;

    public static MemberLoginResponse of(String accessToken, long accessExpirationTime, String message) {
        return MemberLoginResponse.builder()
                .accessToken(accessToken)
                .expiresIn(accessExpirationTime)
                .message(message)
                .build();
    }

    public static MemberLoginResponse of(String message) {
        return MemberLoginResponse.builder()
                .message(message)
                .build();
    }
}