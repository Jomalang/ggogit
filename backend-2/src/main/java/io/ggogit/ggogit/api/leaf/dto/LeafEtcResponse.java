package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeafEtcResponse {

    private Long leafId;
    private String message;

    public static LeafEtcResponse of(Leaf saved, String message) {
        return LeafEtcResponse.builder()
                .leafId(saved.getId())
                .message(message)
                .build();
    }

    public static LeafEtcResponse of(Long leafId, String message) {
        return LeafEtcResponse.builder()
                .leafId(leafId)
                .message(message)
                .build();
    }
}
