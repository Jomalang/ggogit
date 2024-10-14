package io.ggogit.ggogit.domain.tree.repository;

import io.ggogit.ggogit.domain.tree.entity.Seed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeedRepository extends JpaRepository<Seed, Long> {
}
