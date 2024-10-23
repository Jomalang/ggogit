package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.leaf.structure.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class LeafItemResponse {

    private List<LeafItemDto> items;

    public LeafItemResponse() {
        this.items = new ArrayList<>();
    }



    public void addItem(TreeNode node, List<LeafTag> tags) {
        items.add(LeafItemDto.of(node, tags));
    }

    @Builder
    @Data
    @AllArgsConstructor
    private static class LeafItemDto {
        private Long id;
        private Long parentLeafId;
        private List<Long> childLeafIds;
        private String title;
        private List<LeafTagDto> tags;
        private Boolean visibility;
        private Boolean focused; // 현재 사용자가 보고 있는 리프인지
        private Integer direction;
        private String createTime;

        public static LeafItemDto of(TreeNode node, List<LeafTag> tags) {
            return LeafItemDto.builder()
                    .id(node.getValue().getId())
                    .parentLeafId(
                            node.getValue() == null ? null :
                            node.getValue().getParentLeaf() == null ? null :
                            node.getValue().getParentLeaf().getId())
                    .visibility(node.getValue().getVisibility())
                    .childLeafIds(node.getChildren().stream().map(leafNode -> leafNode.getValue().getId()).toList())
                    .title(node.getValue().getTitle())
                    .tags(tags.stream().map(LeafTagDto::of).toList())
                    .focused(false)
                    .direction(node.getDirection().getNum())
                    .createTime(
                            node.getValue().getCreateTime() == null ? "" :
                            node.getValue().getCreateTime().toLocalDate().toString()
                    )
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class LeafTagDto {
        private Long id;
        private String name;

        public static LeafTagDto of(LeafTag leafTag) {
            return new LeafTagDto(leafTag.getId(), leafTag.getName());
        }

    }
}
