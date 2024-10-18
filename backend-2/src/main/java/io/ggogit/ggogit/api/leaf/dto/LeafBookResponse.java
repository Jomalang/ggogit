package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeafBookResponse {

    private Long leafId;
    private String message;

    public static LeafBookResponse of(LeafBook saved, String message) {
        return LeafBookResponse.builder()
                .leafId(saved.getLeaf().getId())
                .message(message)
                .build();
    }

    public static LeafBookResponse of(Long leafId, String message) {
        return LeafBookResponse.builder()
                .leafId(leafId)
                .message(message)
                .build();
    }
}
