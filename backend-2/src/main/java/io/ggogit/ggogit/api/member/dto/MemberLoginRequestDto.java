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
public class MemberLoginRequestDto {

    private String email;
    private String password;

    public Member toMember() {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        return member;
    }
}
