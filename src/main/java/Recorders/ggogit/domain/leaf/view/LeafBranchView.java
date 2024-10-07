package Recorders.ggogit.domain.leaf.view;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBranchView {
    Long id;
    Long parentLeafId;
    String title;
    Long leafCount;
    Long viewCount;
    Boolean bookMark;
    Boolean visibility;
    LocalDateTime updateTime;

    public static LeafBranchView of(Leaf leaf, Long viewCount, Long leafCount){
        return LeafBranchView.builder()
                .id(leaf.getId())
                .title(leaf.getTitle())
                .bookMark(leaf.getBookMark())
                .updateTime(leaf.getUpdateTime())
                .parentLeafId(leaf.getParentLeafId())
                .viewCount(viewCount)
                .leafCount(leafCount)
                .build();
    }
}
