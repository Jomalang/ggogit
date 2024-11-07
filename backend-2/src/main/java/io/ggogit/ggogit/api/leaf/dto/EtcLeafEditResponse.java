package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtcLeafEditResponse {

    private List<LeafTagDto> tags;
    private String title;
    private String content;
    private Boolean visibility;

    public static EtcLeafEditResponse of(Leaf leaf, List<LeafTag> tags) {
        return EtcLeafEditResponse.builder()
                .tags(tags.stream().map(LeafTagDto::of).toList())
                .title(leaf.getTitle())
                .content(leaf.getContent())
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