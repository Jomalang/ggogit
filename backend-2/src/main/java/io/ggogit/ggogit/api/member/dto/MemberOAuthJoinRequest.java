package io.ggogit.ggogit.api.member.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberOAuthJoinRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "소개를 입력해주세요.")
    private String introduction;

    private String picture;

    public Member toMember() {
        return Member.builder()
                .username(name)
                .email(email)
                .nickname(nickname)
                .introduction(introduction)
                .build();
    }
}
