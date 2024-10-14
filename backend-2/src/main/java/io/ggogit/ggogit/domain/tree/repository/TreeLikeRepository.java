package io.ggogit.ggogit.domain.tree.repository;

import io.ggogit.ggogit.domain.tree.entity.TreeLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeLikeRepository extends JpaRepository<TreeLike, Long> {
}
