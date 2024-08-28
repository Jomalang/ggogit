package Recorders.ggogit.domain.leaf.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TagDomain {
    private Long id;
    // TODO: domain 수정
    // private MemberDomain memberId;
    private String name;
}