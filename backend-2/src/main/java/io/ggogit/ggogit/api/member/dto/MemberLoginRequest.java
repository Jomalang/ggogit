package io.ggogit.ggogit.api.member.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginRequest {

    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public Member toMember() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
