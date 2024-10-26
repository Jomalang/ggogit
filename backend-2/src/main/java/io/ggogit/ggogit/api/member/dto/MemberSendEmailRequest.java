package io.ggogit.ggogit.api.member.dto;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSendEmailRequest {

    // 이메일 확인 정규식
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

}