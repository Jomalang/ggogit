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

    private Long tagId;
    private String message;

    public static LeafTagResponse of(LeafTag leafTag, String message) {
        return LeafTagResponse.builder()
                .tagId(leafTag.getId())
                .message(message)
                .build();
    }

    public static LeafTagResponse of(Long leafTagId, String message) {
        return LeafTagResponse.builder()
                .tagId(leafTagId)
                .message(message)
                .build();
    }
}