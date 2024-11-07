package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
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
public class BookLeafEditResponse {

    private Integer startPage;
    private Integer endPage;
    private List<TagDto> tags;
    private String title;
    private String content;
    private Boolean visibility;

    public static BookLeafEditResponse of(Leaf leaf, LeafBook leafBook, List<LeafTag> tags) {
        return BookLeafEditResponse.builder()
                .startPage(leafBook.getStartPage())
                .endPage(leafBook.getEndPage())
                .tags(tags.stream().map(TagDto::of).toList())
                .title(leaf.getTitle())
                .content(leaf.getContent())
                .visibility(leaf.getVisibility())
                .build();
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class TagDto {
        private Long id;
        private String name;

        public static TagDto of(LeafTag tag) {
            return TagDto.builder()
                    .id(tag.getId())
                    .name(tag.getName())
                    .build();
        }
    }
}
