package io.ggogit.ggogit.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLogoutResponse {

    // 로그아웃 성공 여부
    private boolean success;
    // 로그아웃 실패 이유
    private String message;

    public static MemberLogoutResponse of(String message) {
        return MemberLogoutResponse.builder()
                .success(true)
                .message(message)
                .build();
    }
}
