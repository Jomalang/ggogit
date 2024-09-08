package Recorders.ggogit.web.member.form;

import Recorders.ggogit.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRegForm {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
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
