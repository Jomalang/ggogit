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
    private Long viewCount;
    private Long likeCount;
    private Boolean visibility;
    private Boolean bookMark;
    private Long childLeafCount;
    private List<Long> tagIds;
    private List<LeafTag> tags;

    public static LeafEtcView of(Leaf leaf, List<LeafTag> tags) {
        return LeafEtcView.builder()
                .leafId(leaf.getId())
                .treeId(leaf.getTreeId())
                .parentLeafId(leaf.getParentLeafId())
                .title(leaf.getTitle())
                .content(leaf.getContent())
                .visibility(leaf.getVisibility())
                .childLeafCount(leaf.getChildLeafCount())
                .tags(tags)
                .build();
    }

    public Leaf toLeaf() {
        return Leaf.builder()
                .id(leafId)
                .treeId(treeId)
                .parentLeafId(parentLeafId)
                .visibility(visibility)
                .viewCount(viewCount)
                .likeCount(likeCount)
                .title(title)
                .content(content)
                .childLeafCount(childLeafCount)
                .bookMark(bookMark)
                .build();
    }
}