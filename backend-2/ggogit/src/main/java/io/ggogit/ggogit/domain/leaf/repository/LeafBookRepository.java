package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeafBookRepository extends JpaRepository<LeafBook, Long> {
}
