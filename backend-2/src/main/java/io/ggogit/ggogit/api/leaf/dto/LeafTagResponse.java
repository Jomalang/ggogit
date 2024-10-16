package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeafTagResponse {

    private Long id;
    private String message;

    public static LeafTagResponse of(LeafTag leafTag, String message) {
        return LeafTagResponse.builder()
                .id(leafTag.getId())
                .message(message)
                .build();
    }

    public static LeafTagResponse of(Long leafTagId, String message) {
        return LeafTagResponse.builder()
                .id(leafTagId)
                .message(message)
                .build();
    }
}