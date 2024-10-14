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
public class EtcLeafResponse {

    private Long leafId;
    private String message;

    public static EtcLeafResponse of(Leaf saved, String message) {
        return EtcLeafResponse.builder()
                .leafId(saved.getId())
                .message(message)
                .build();
    }

    public static EtcLeafResponse of(Long leafId, String message) {
        return EtcLeafResponse.builder()
                .leafId(leafId)
                .message(message)
                .build();
    }
}
