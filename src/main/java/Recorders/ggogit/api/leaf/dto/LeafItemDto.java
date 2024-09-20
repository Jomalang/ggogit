package Recorders.ggogit.api.leaf.dto;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.structure.LeafDirectionType;
import Recorders.ggogit.domain.leaf.structure.LeafNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafItemDto {
    private Long id;
    private Long parentLeafId;
    private List<Long> childLeafIds;
    private String title;
    private List<LeafTag> tags;
    private Boolean focused; // 현재 사용자가 보고 있는 리프인지
    private LeafDirectionType direction;
    private LocalDateTime createTime;

    public static LeafItemDto of(LeafNode node, List<LeafTag> tags) {
        return LeafItemDto.builder()
                .id(node.getData().getId())
                .parentLeafId(node.getData().getParentLeafId())
                .childLeafIds(node.getChildren().stream().map(leafNode -> leafNode.getData().getId()).toList())
                .title(node.getData().getTitle())
                .tags(tags)
                .focused(false)
                .direction(node.getDirection())
                .createTime(node.getData().getCreateTime())
                .build();
    }
}