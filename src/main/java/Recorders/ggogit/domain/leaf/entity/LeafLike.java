package Recorders.ggogit.domain.leaf.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafLike {
    private Long memberId; // TODO: entity 수정
    private Long leafId; // TODO: entity 수정
    private boolean activate;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
