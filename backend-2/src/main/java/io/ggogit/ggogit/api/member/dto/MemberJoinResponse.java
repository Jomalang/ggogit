package io.ggogit.ggogit.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberJoinResponse {

    // 회원가입 성공 여부
    private boolean success;

    // 회원가입 실패 이유
    private String message;

    public static MemberJoinResponse of(String message) {
        return MemberJoinResponse.builder()
                .success(true)
                .message(message)
                .build();
    }

    public static MemberJoinResponse of(boolean isSuccess, String message) {
        return MemberJoinResponse.builder()
                .success(isSuccess)
                .message(message)
                .build();
    }
}