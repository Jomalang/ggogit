package io.ggogit.ggogit.api.member.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.entity.MemberBackgroundImage;
import io.ggogit.ggogit.domain.member.entity.MemberProfileImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponse {

    private Long id;
    private String email;
    private String nickname;
    private String username;
    private String introduction;
    private String memberProfileImage;
    private String memberBackgroundImage;

    public static MemberResponse of(Long id,
                                    String email,
                                    String nickname,
                                    String username,
                                    String introduction,
                                    MemberProfileImage memberProfileImage,
                                    MemberBackgroundImage memberBackgroundImage) {
        return MemberResponse.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .username(username)
                .introduction(introduction)
                .memberProfileImage(memberProfileImage.getName())
                .memberBackgroundImage(memberBackgroundImage.getName())
                .build();
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .username(member.getUsername())
                .introduction(member.getIntroduction())
                .memberProfileImage(member.getMemberProfileImage().getName())
                .memberBackgroundImage(member.getMemberBackgroundImage().getName())
                .build();
    }
}
