package Recorders.ggogit.api.leaf.dto;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.structure.LeafNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<LeafTagDto> tags;
    private Boolean visibility;
    private Boolean focused; // 현재 사용자가 보고 있는 리프인지
    private Integer direction;
    private String createTime;

    public static LeafItemDto of(LeafNode node, List<LeafTag> tags) {
        return LeafItemDto.builder()
                .id(node.getData().getId())
                .parentLeafId(node.getData().getParentLeafId())
                .visibility(node.getData().getVisibility())
                .childLeafIds(node.getChildren().stream().map(leafNode -> leafNode.getData().getId()).toList())
                .title(node.getData().getTitle())
                .tags(tags.stream().map(LeafTagDto::of).toList())
                .focused(false)
                .direction(node.getDirection().getNum())
                .createTime(
                        node.getData().getCreateTime() == null ? "" :
                        node.getData().getCreateTime().toLocalDate().toString()
                )
                .build();
    }
}