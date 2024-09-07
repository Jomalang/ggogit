package Recorders.ggogit.domain.leaf.view;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafEtcView {
    private Long leafId;
    private Long treeId;
    private Long parentLeafId;
    private String title;
    private String content;
    private Boolean visibility;
    private Integer childLeafCount;
    private List<LeafTag> tags;

    public Leaf toLeaf() {
        return Leaf.builder()
                .id(leafId)
                .treeId(treeId)
                .parentLeafId(parentLeafId)
                .visibility(visibility)
                .viewCount(0)
                .likeCount(0)
                .title(title)
                .content(content)
                .childLeafCount(childLeafCount)
                .bookMark(0L)
                .build();
    }
}