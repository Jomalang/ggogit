package Recorders.ggogit.domain.leaf.domain;


import Recorders.ggogit.domain.leaf.entity.LeafTag;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LeafTagDomain {
    private Long id;
    // TODO: domain 수정
    private Long memberId;
    private String name;

    public LeafTag toEntity() {
        return LeafTag.builder()
                .id(id)
                .memberId(memberId)
                .name(name)
                .build();
    }
}