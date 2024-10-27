package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
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
public class LeafBookEditDetailResponse {

    private Integer startPage;
    private Integer endPage;
    private List<LeafTagDto> tags;
    private String title;
    private String content;
    private Boolean visibility;

    public static LeafBookEditDetailResponse of(LeafBook leafBook, Leaf leaf, List<LeafTag> tags) {
        return LeafBookEditDetailResponse.builder()
                .startPage(leafBook.getStartPage())
                .endPage(leafBook.getEndPage())
                .tags(tags.stream().map(LeafTagDto::of).toList())
                .title(leaf.getTitle())
                .content(leaf.getContent())
                .visibility(leaf.getVisibility())
                .build();
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    public static class LeafTagDto {
        private Long id;
        private String name;

        public static LeafTagDto of(LeafTag leafTag) {
            return LeafTagDto.of(leafTag.getId(), leafTag.getName());
        }
    }
}