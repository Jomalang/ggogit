package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.LeafCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeafCommentLikeRepository extends JpaRepository<LeafCommentLike, Long> {
}
