package io.ggogit.ggogit.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberOAuthJoinResponse {

    private boolean success;
    private String message;

    public static MemberOAuthJoinResponse of(boolean b, String message) {
        return MemberOAuthJoinResponse.builder()
                .success(b)
                .message(message)
                .build();
    }
}