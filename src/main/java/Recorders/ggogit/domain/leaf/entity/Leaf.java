package Recorders.ggogit.domain.leaf.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Leaf {
    private Long id;
    private Long treeId;
    private Long parentLeafId;
    private Boolean visibility;
    private Long viewCount;
    private Long likeCount;
    private String title;
    private String content;
    private Long childLeafCount;
    private Boolean bookMark;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}