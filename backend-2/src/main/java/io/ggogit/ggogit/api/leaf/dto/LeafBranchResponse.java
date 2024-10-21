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
    private Integer leafCount;
    private Integer viewCount;
    private Integer likeCount;
    private Boolean bookMark;
    private Boolean visibility;
    private LocalDateTime updateTime;

    public static LeafBranchResponse of(Leaf leaf,Integer likeCount, Integer viewCount, Integer leafCount){
        return LeafBranchResponse.builder()
                .id(leaf.getId())
                .title(leaf.getTitle())
                .bookMark(leaf.getBookMark())
                .updateTime(leaf.getUpdateTime())
                .parentLeafId(leaf.getParentLeaf().getId())
                .viewCount(viewCount)
                .leafCount(leafCount)
                .likeCount(likeCount)
                .build();
    }
}
