package io.ggogit.ggogit.api.member.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginResponseDto {
    private String email;
    private String nickname;
    private String username;
    private String introduction;

    public MemberLoginResponseDto(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.username = member.getUsername();
        this.introduction = member.getIntroduction();
    }
}
