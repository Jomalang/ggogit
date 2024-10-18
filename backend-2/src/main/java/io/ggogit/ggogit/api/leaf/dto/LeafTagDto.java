package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeafTagDto {
    private Long tagId;
    private String tagName;

    public static LeafTagDto of(LeafTag leafTag) {
        return LeafTagDto.builder()
                .tagId(leafTag.getId())
                .tagName(leafTag.getName())
                .build();
    }
}
