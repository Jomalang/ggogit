package Recorders.ggogit.domain.leaf.entity;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Leaf {
    private Long id;
    private Long treeId; // TODO: entity 수정
    private Long parentLeafId; // TODO: entity 수정
    private Boolean visibility;
    private Integer viewCount;
    private Integer likeCount;
    private String title;
    private Long text;
    private Integer childLeafCount;
    private Long bookMark;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}