package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.LeafComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeafCommentRepository extends JpaRepository<LeafComment, Long> {
}
