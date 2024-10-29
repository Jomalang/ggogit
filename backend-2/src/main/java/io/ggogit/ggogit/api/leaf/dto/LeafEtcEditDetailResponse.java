package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafEtcEditDetailResponse {

    private String title;
    private String content;
    private List<LeafTagDto> tags;
    private Boolean visibility;

    public static LeafEtcEditDetailResponse of(Leaf leaf, List<LeafTag> leafTags) {
        return LeafEtcEditDetailResponse.builder()
                .title(leaf.getTitle())
                .content(leaf.getContent())
                .tags(leafTags.stream().map(LeafTagDto::of).toList())
                .visibility(leaf.getVisibility())
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