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
public class MemberJoinRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

    @NotBlank(message = "가입 토큰을 입력해주세요.")
    private String joinToken;

    @Email
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "한줄 소개를 입력해주세요.")
    private String introduction;

    public Member toMember() {
        return Member.builder()
                .username(username)
                .email(email)
                .nickname(nickname)
                .password(password)
                .introduction(introduction)
                .build();
    }
}