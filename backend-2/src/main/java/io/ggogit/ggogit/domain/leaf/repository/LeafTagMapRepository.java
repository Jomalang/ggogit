package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.leaf.entity.LeafTagMap;
import io.ggogit.ggogit.domain.leaf.entity.LeafTagMapId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeafTagMapRepository extends JpaRepository<LeafTagMap, LeafTagMapId> {
    List<LeafTagMap> findByLeaf(Leaf leaf);
    List<LeafTagMap> findByLeafTag(LeafTag leafTag);
}
