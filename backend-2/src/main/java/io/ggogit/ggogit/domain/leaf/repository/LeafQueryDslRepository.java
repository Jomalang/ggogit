package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;

import java.util.Optional;

public interface LeafQueryDslRepository {

    Optional<Leaf> findByLeafId(Long leafId);

}