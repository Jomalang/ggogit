package io.ggogit.ggogit.domain.member.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponseDto {
    private String name;
    private String picture;
    private String email;
    private String nickname;

    public AuthResponseDto(String email, String name, String picture, String nickname) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.nickname = nickname;
    }

    public AuthResponseDto of(String email, String name, String picture, String nickname) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.nickname = nickname;
        return this;
    }

    public AuthResponseDto of(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        return this;
    }

}
