package Recorders.ggogit.domain.leaf;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Leaf {
    private Long id;
    private Long treeId;
    private Long parentLeafId;
    private Boolean visibility;
    private Long viewCount;
    private Long likeCount;
    private String title;
    private String text;
    private Long bookMark;
    private Long childLeafCount;
    private Timestamp updateTime;
    private Timestamp createTime;
}