package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeafRepository extends JpaRepository<Leaf, Long> {
}
