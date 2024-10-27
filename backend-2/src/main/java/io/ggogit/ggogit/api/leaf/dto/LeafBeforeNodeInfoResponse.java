package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeafBeforeNodeInfoResponse {

    private String title;
    private String createTime; // 2024-09-20T09:17:19.994
    private List<LeafTagDto> tags;

    public static LeafBeforeNodeInfoResponse of(Leaf leaf, List<LeafTag> tags) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LeafBeforeNodeInfoResponse.builder()
                .title(leaf.getTitle())
                .createTime(leaf.getCreateTime().format(formatter))
                .tags(tags.stream().map(LeafTagDto::of).toList())
                .build();
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    private static class LeafTagDto {
        private Long id;
        private String name;

        public static LeafTagDto of(LeafTag leafTag) {
            return LeafTagDto.of(leafTag.getId(), leafTag.getName());
        }
    }
}