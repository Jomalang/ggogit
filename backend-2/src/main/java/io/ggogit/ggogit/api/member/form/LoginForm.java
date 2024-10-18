package io.ggogit.ggogit.api.member.form;


import io.ggogit.ggogit.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public Member toMember(){
        Member member = new Member();

        member.setEmail(email);
        member.setPassword(password);

        return member;

    }
}
