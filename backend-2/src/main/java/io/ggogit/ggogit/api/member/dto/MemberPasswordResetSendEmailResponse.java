package io.ggogit.ggogit.api.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberPasswordResetSendEmailResponse {

    // 비밀번호 변경 이메일 전송 성공 여부
    private boolean success;
    // 비밀번호 변경 실패 이유
    private String message;

    public static MemberPasswordResetSendEmailResponse of(String message) {
        return MemberPasswordResetSendEmailResponse.builder()
                .success(true)
                .message(message)
                .build();
    }
}
