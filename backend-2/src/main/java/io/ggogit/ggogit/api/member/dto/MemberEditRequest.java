package io.ggogit.ggogit.api.member.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberEditRequest {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

    @NotBlank(message = "한줄 소개를 입력해주세요.")
    private String introduction;

    public Member toMember() {
        return Member.builder()
                .nickname(nickname)
                .username(username)
                .introduction(introduction)
                .build();
    }
}