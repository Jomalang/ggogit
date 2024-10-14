package io.ggogit.ggogit.domain.tree.repository;

import io.ggogit.ggogit.domain.tree.entity.TreeBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeBookRepository extends JpaRepository<TreeBook, Long> {
}
