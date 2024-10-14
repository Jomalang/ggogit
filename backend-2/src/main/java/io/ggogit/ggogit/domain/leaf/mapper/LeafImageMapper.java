package io.ggogit.ggogit.domain.leaf.mapper;

import io.ggogit.ggogit.domain.leaf.entity.LeafImage;
import org.springframework.stereotype.Component;

@Component
public class LeafImageMapper {
    public LeafImage toEntity(Long id, String fileName) {
        return LeafImage.builder()
                .id(id)
                .name(fileName)
                .build();
    }
}
