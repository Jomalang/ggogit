package io.ggogit.ggogit.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSendEmailResponse {

    // 전송 성공 여부
    private boolean success;
    // 메시지
    private String message;

    public static MemberSendEmailResponse of(String message) {
        return MemberSendEmailResponse.builder()
                .success(true)
                .message(message)
                .build();
    }

    public static MemberSendEmailResponse of(boolean isSuccess, String message) {
        return MemberSendEmailResponse.builder()
                .success(isSuccess)
                .message(message)
                .build();
    }
}