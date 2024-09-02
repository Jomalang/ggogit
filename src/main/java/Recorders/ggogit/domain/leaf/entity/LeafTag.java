package Recorders.ggogit.domain.leaf.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafTag {
    private Long id;
    private Long memberId; // TODO: entity 수정
    private String name;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}