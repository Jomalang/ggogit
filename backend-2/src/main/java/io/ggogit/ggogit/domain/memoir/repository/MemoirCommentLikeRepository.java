package io.ggogit.ggogit.domain.memoir.repository;

import io.ggogit.ggogit.domain.memoir.entity.MemoirCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoirCommentLikeRepository extends JpaRepository<MemoirCommentLike, Long> {
}
