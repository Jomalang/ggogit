package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LeafTagDetailResponse {

    private Long id;
    private String name;

    public static LeafTagDetailResponse of(LeafTag leafTag) {
        return LeafTagDetailResponse.builder()
                .id(leafTag.getId())
                .name(leafTag.getName())
                .build();
    }
}
