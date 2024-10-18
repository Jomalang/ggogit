package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeafDetailResponse {

    private Long leafId;
    private Long treeId;
    @Nullable
    private Long parentLeafId;
    @Nullable
    private Integer startPage;
    @Nullable
    private Integer endPage;
    private String title;
    private String content;
    private Boolean visibility;
    private Integer childLeafCount;
    private List<LeafTagDto> tags;

    public static LeafDetailResponse of(Leaf leaf, LeafBook leafBook, List<LeafTag> leafTags) {
        return LeafDetailResponse.builder()
                .leafId(leaf.getId())
                .treeId(leaf.getTree().getId())
                .parentLeafId(leaf.getParentLeaf().getId())
                .startPage(leafBook.getStartPage())
                .endPage(leafBook.getEndPage())
                .tags(leafTags.stream().map(LeafTagDto::of).toList())
                .title(leaf.getTitle())
                .content(leaf.getContent())
                .visibility(leaf.getVisibility())
                .childLeafCount(leaf.getChildLeafCount())
                .build();
    }

    public static LeafDetailResponse of(Leaf leaf, List<LeafTag> leafTags) {
        return LeafDetailResponse.builder()
                .leafId(leaf.getId())
                .treeId(leaf.getTree().getId())
                .parentLeafId(leaf.getParentLeaf().getId())
                .startPage(null)
                .endPage(null)
                .tags(leafTags.stream().map(LeafTagDto::of).toList())
                .title(leaf.getTitle())
                .content(leaf.getContent())
                .visibility(leaf.getVisibility())
                .childLeafCount(leaf.getChildLeafCount())
                .build();
    }
}