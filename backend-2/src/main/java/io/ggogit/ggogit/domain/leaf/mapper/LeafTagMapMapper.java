package io.ggogit.ggogit.domain.leaf.mapper;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.leaf.entity.LeafTagMap;
import io.ggogit.ggogit.domain.leaf.entity.LeafTagMapId;
import org.springframework.stereotype.Component;

@Component
public class LeafTagMapMapper {
    public LeafTagMap toEntity(Leaf leaf, LeafTag leafTag) {
        return LeafTagMap.builder()
                .id(LeafTagMapId.of(leaf.getId(), leafTag.getId()))
                .leaf(leaf)
                .leafTag(leafTag)
                .build();
    }
}
