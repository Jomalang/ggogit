package io.ggogit.ggogit.domain.member.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDto {
    private String email;
    private String name;
    private String picture;
    private String nickname;

    public static AuthResponseDto of(String email, String name, String picture, String nickname) {
        return AuthResponseDto.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .nickname(nickname)
                .build();
    }

    public static AuthResponseDto of(String email, String name, String picture) {
        return AuthResponseDto.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .build();
    }

}
