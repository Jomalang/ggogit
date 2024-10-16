package io.ggogit.ggogit.api.member.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberImageResponse {
    private Long id;
    private String nickName;
    private String userName;
    private String profileImageUrl;
    private String backgroundImageUrl;

    public MemberImageResponse of(Member member) {
        return MemberImageResponse.builder()
                .id(member.getId())
                .nickName(member.getNickname())
                .userName(member.getUsername())
                .profileImageUrl(member.getMemberProfileImage().getName())
                .backgroundImageUrl(member.getMemberBackgroundImage().getName())
                .build();
    }
}
