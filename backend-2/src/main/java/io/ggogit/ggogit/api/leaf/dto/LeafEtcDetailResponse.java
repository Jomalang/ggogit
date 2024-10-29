package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafEtcDetailResponse {
    private String title;
    private String content;

    public static LeafEtcDetailResponse of(Leaf leaf) {
        return LeafEtcDetailResponse.builder()
                .title(leaf.getTitle())
                .content(leaf.getContent())
                .build();
    }
}