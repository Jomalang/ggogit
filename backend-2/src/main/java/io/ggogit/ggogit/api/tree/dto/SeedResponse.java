package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.Seed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeedResponse {

    List<SeedDto> items;

    public static SeedResponse of(List<Seed> seeds) {
        return SeedResponse.builder()
                .items(seeds.stream().map(SeedDto::of).toList())
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SeedDto {
        private Long id;
        private String korName;
        private String engName;

        public static SeedDto of(Seed seed) {
            return SeedDto.builder()
                    .id(seed.getId())
                    .korName(seed.getKorName())
                    .engName(seed.getEngName())
                    .build();
        }
    }
}