package Recorders.ggogit.domain.leaf.mapper;

import Recorders.ggogit.domain.leaf.entity.LeafImage;
import org.springframework.stereotype.Component;

@Component
public class LeafMapper {
    public LeafImage toEntity(Long leafId, String fileName) {
        return LeafImage.builder()
                .leafId(leafId)
                .name(fileName)
                .build();
    }
}