package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBranchResponse {
    private Long id;
    private Long parentLeafId;
    private String title;
    private Long leafCount;
    private Long viewCount;
    private Boolean bookMark;
    private Boolean visibility;
    private LocalDateTime updateTime;

    public static LeafBranchResponse of(Leaf leaf, Long viewCount, Long leafCount){
        return LeafBranchResponse.builder()
                .id(leaf.getId())
                .title(leaf.getTitle())
                .bookMark(leaf.getBookMark())
                .updateTime(leaf.getUpdateTime())
                .parentLeafId(leaf.getParentLeaf().getId())
                .viewCount(viewCount)
                .leafCount(leafCount)
                .build();
    }
}
