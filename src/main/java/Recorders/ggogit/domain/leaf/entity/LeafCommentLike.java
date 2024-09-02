package Recorders.ggogit.domain.leaf.entity;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LeafCommentLike {
    private Long memberId; // TODO: entity 수정
    private Long leafCommentId; // TODO: entity 수정
    private Boolean activate;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}