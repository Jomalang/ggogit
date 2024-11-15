package io.ggogit.ggogit.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCheckTokenResponse {

    private boolean valid;

    public static MemberCheckTokenResponse of(boolean valid) {
        return MemberCheckTokenResponse.builder()
                .valid(valid)
                .build();
    }
}
