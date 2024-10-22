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
public class MemberRegRequestDto {

    private String email;
    private String password;
    private String nickname;
    private String username;
    private String introduction;
    private Boolean policyAgreement;

    public Member toMember() {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setNickname(nickname);
        member.setUsername(username);
        member.setIntroduction(introduction);
        return member;
    }
}
