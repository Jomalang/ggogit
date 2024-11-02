package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.Seed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeedDetailResponse {
    private Long id;
    private String name;

    public static SeedDetailResponse of(Seed seed) {
        return SeedDetailResponse.builder()
                .id(seed.getId())
                .name(seed.getKorName())
                .build();
    }
}