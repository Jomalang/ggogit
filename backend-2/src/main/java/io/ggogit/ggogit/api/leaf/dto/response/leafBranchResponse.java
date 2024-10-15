package io.ggogit.ggogit.api.leaf.dto.response;

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
public class leafBranchResponse {
    Long id;
    Long parentLeafId;
    String title;
    Long leafCount;
    Long viewCount;
    Boolean bookMark;
    Boolean visibility;
    LocalDateTime updateTime;

    public static leafBranchResponse of(Leaf leaf, Long viewCount, Long leafCount){
        return leafBranchResponse.builder()
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
