package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBookDetailResponse {

    private String title;
    private String content;
    private Integer startPage;
    private Integer endPage;

    public static LeafBookDetailResponse of(Leaf leaf, LeafBook leafBook) {
        return LeafBookDetailResponse.builder()
                .title(leaf.getTitle())
                .content(leaf.getContent())
                .startPage(leafBook.getStartPage())
                .endPage(leafBook.getEndPage())
                .build();
    }
}