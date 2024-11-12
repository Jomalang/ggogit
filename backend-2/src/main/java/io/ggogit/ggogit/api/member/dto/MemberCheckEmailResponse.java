package io.ggogit.ggogit.api.member.dto;

import io.ggogit.ggogit.domain.member.entity.EmailJoinToken;
import io.ggogit.ggogit.domain.member.entity.PassWordRest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCheckEmailResponse {

    private String email;

    public static MemberCheckEmailResponse of(EmailJoinToken emailJoinToken) {
        return MemberCheckEmailResponse.builder()
                .email(emailJoinToken.getEmail())
                .build();
    }

    public static MemberCheckEmailResponse of(PassWordRest passWordRest) {
        return MemberCheckEmailResponse.builder()
                .email(passWordRest.getEmail())
                .build();
    }
}