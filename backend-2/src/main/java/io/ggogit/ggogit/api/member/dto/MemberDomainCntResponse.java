package io.ggogit.ggogit.api.member.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDomainCntResponse {

    private Long memberId;
    private Long treeCnt;
    private Long leafCnt;
    private Long memoirCnt;
    private Long bookCnt;

}
