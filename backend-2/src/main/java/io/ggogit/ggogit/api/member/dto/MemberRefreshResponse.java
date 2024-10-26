package io.ggogit.ggogit.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRefreshResponse {

    private String accessToken;
    private Long expiresIn;
    private String message;

    public static MemberRefreshResponse of(String token, long accessExpirationTime) {
        return MemberRefreshResponse.builder()
                .accessToken(token)
                .expiresIn(accessExpirationTime)
                .build();
    }

    public static MemberRefreshResponse of(String message) {
        return MemberRefreshResponse.builder()
                .message(message)
                .build();
    }
}