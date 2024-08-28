package Recorders.ggogit.domain.leaf.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LeafTag {
    private Long id;
    private Long memberId; // TODO: entity 수정
    private Long name;
    private Long updateTime;
    private Long createTime;
}