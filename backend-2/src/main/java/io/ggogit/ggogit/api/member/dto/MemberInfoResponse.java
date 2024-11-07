package io.ggogit.ggogit.api.member.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberInfoResponse {

    private Long id;
    private String nickName;
    private String userName;
    private String email;
    private String backImgName;
    private String profileImgName;

    public static MemberInfoResponse of(Member member) {
        return MemberInfoResponse.builder()
                .id(member.getId())
                .userName(member.getUsername())
                .nickName(member.getNickname())
                .email(member.getEmail())
                .backImgName(member.getMemberBackgroundImage() == null ? null : member.getMemberBackgroundImage().getName())
                .profileImgName(member.getMemberProfileImage() == null ? null : member.getMemberProfileImage().getName())
                .build();
    }

    // 이메일 뒷부분 숨기기
    public static MemberInfoResponse ofNoEmailDomain(Member member) {
        return MemberInfoResponse.builder()
                .id(member.getId())
                .userName(member.getUsername())
                .nickName(member.getNickname())
                .email(member.getEmail().split("@")[0])
                .backImgName(member.getMemberBackgroundImage() == null ? null : member.getMemberBackgroundImage().getName())
                .profileImgName(member.getMemberProfileImage() == null ? null : member.getMemberProfileImage().getName())
                .build();
    }
}