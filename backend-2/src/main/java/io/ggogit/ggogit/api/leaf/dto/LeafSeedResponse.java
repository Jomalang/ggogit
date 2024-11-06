package io.ggogit.ggogit.api.leaf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LeafSeedResponse {

    // 리프 리스트 조회할 떄 사용
    String seedType; // book or etc

    public static LeafSeedResponse of(String seedType) {
        return LeafSeedResponse.builder()
                .seedType(seedType)
                .build();
    }
}