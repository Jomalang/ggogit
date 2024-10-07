package Recorders.ggogit.domain.leaf.view;


import Recorders.ggogit.domain.leaf.structure.LeafDirectionType;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.structure.LeafNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafItemView {
    private Long id;
    private Long parentLeafId;
    private String title;
    private List<LeafTag> tags;
    private Boolean focused; // 현재 사용자가 보고 있는 리프인지
    private LeafDirectionType direction;
    private LocalDateTime createTime;

    public static LeafItemView of(LeafNode leafNode, List<LeafTag> tags) {
        return LeafItemView.builder()
                .id(leafNode.getData().getId())
                .parentLeafId(leafNode.getData().getParentLeafId())
                .title(leafNode.getData().getTitle())
                .tags(tags)
                .focused(false)
                .direction(leafNode.getDirection())
                .createTime(leafNode.getData().getCreateTime())
                .build();
    }
    
    public Date getCreateTime() {
        if (createTime == null) { return null; }
        return Date.from(createTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }
}