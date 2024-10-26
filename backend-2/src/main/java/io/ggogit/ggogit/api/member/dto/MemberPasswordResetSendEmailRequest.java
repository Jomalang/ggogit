package io.ggogit.ggogit.api.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberPasswordResetSendEmailRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
}