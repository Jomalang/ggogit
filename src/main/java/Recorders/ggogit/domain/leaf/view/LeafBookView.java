package Recorders.ggogit.domain.leaf.view;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.entity.LeafBook;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBookView {

    private Long leafId;
    @Nullable
    private Long treeId;
    @Nullable
    private Long parentLeafId;
    private Integer startPage;
    private Integer endPage;
    private String title;
    private String content;
    private Boolean visibility;
    private Long childLeafCount;
    private List<LeafTag> tags;

    public static LeafBookView of(Leaf leaf, LeafBook leafBook, List<LeafTag> tags) {
        return LeafBookView.builder()
                .leafId(leaf.getId())
                .treeId(leaf.getTreeId())
                .parentLeafId(leaf.getParentLeafId())
                .startPage(leafBook.getStartPage())
                .endPage(leafBook.getEndPage())
                .tags(tags)
                .title(leaf.getTitle())
                .content(leaf.getContent())
                .visibility(leaf.getVisibility())
                .childLeafCount(leaf.getChildLeafCount())
                .build();
    }

    public Leaf updateLeaf(Leaf leaf) {
        leaf.setTitle(title);
        leaf.setContent(content);
        leaf.setVisibility(visibility);
        return leaf;
    }

    public Leaf toLeaf(Long parentLeafId) {
        return Leaf.builder()
                .id(leafId)
                .treeId(treeId)
                .parentLeafId(parentLeafId)
                .visibility(visibility)
                .viewCount(0L)
                .likeCount(0L)
                .title(title)
                .content(content)
                .childLeafCount(childLeafCount)
                .bookMark(0L)
                .build();
    }

    public Leaf toLeaf() {
        return Leaf.builder()
                .id(leafId)
                .treeId(treeId)
                .parentLeafId(parentLeafId)
                .visibility(visibility)
                .viewCount(0L)
                .likeCount(0L)
                .title(title)
                .content(content)
                .childLeafCount(childLeafCount)
                .bookMark(0L)
                .build();
    }

    public LeafBook toLeafBook() {
        return LeafBook.builder()
                .leafId(leafId)
                .startPage(startPage)
                .endPage(endPage)
                .build();
    }

    public LeafBook toLeafBook(Long leafleafId) {
        return LeafBook.builder()
                .leafId(leafleafId)
                .startPage(startPage)
                .endPage(endPage)
                .build();
    }
}