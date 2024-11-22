package io.ggogit.ggogit.api.member.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.entity.MemberBackgroundImage;
import io.ggogit.ggogit.domain.member.entity.MemberProfileImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static java.util.Optional.*;
import static org.eclipse.jdt.internal.compiler.problem.ProblemSeverities.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponse {

    private Long id;
    private String email;
    private String nickname;
    private String username;
    @Builder.Default
    private String introduction = "";

    @Builder.Default
    private String memberProfileImage = "";

    @Builder.Default
    private String memberBackgroundImage = "";

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
                .introduction(ofNullable(introduction).orElse(""))
                .memberProfileImage(ofNullable(memberProfileImage).map(MemberProfileImage::getName).orElse(""))
                .memberBackgroundImage(ofNullable(memberBackgroundImage).map(MemberBackgroundImage::getName).orElse(""))
                .build();
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .username(member.getUsername())
                .introduction(ofNullable(member.getIntroduction()).orElse(""))
                .memberProfileImage(ofNullable(member.getMemberProfileImage()).map(MemberProfileImage::getName).orElse(""))
                .memberBackgroundImage(ofNullable(member.getMemberBackgroundImage()).map(MemberBackgroundImage::getName).orElse(""))
                .build();
    }
}
