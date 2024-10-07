package Recorders.ggogit.domain.leaf.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafCommentLike {
    private Long memberId; // TODO: entity 수정
    private Long leafCommentId; // TODO: entity 수정
    private Boolean activate;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}