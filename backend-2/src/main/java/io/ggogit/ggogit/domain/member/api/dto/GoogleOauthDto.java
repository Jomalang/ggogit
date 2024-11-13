package io.ggogit.ggogit.domain.member.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleOauthDto {
    private String sub;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String email;
    private boolean email_verified;
    private String locale;
    private String nickname;

    public GoogleOauthDto(String email, String name, String picture, String nickname) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.nickname = nickname;
    }

    public GoogleOauthDto of(String email, String name, String picture, String nickname) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.nickname = nickname;
        return this;
    }

    public GoogleOauthDto of(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        return this;
    }
}
