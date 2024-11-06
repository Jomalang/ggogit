package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafDetailResponse {

    private String leafTitle;
    private String leafContent;
    private String updateDate;
    private Integer leafViewCount;
    private Integer startPage;
    private Integer endPage;

    public static LeafDetailResponse of(Leaf leaf, @Nullable LeafBook leafBook) {
        return LeafDetailResponse.builder()
                .leafTitle(leaf.getTitle())
                .leafContent(leaf.getContent())
                .updateDate(leaf.getUpdateTime().toString())
                .leafViewCount(leaf.getViewCount())
                .startPage(leafBook == null ? null : leafBook.getStartPage())
                .endPage(leafBook == null ? null : leafBook.getEndPage())
                .build();
    }
}