package io.ggogit.ggogit.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberEditResponse {

    private boolean success;
    private String message;

    public static MemberEditResponse of(String message) {
        return MemberEditResponse.builder()
                .success(true)
                .message(message)
                .build();
    }
}