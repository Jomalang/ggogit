package io.ggogit.ggogit.api.member.dto;


import io.ggogit.ggogit.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRegRequest {


    private String email;

    private String password;

    private String nickname;

    private String username;
    private String introduction;
    private Boolean policyAgreement;

    public Member toMember() {
        return null;
    }

    public Object getConfirmPassword() {
        
    }

    public String getName() {
    }
}
