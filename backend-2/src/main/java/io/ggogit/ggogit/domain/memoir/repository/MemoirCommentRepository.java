package io.ggogit.ggogit.domain.memoir.repository;

import io.ggogit.ggogit.domain.memoir.entity.MemoirComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoirCommentRepository extends JpaRepository<MemoirComment, Long> {
}
