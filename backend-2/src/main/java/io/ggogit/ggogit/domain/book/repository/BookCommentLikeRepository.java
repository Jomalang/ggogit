package io.ggogit.ggogit.domain.book.repository;

import io.ggogit.ggogit.domain.book.entity.BookCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCommentLikeRepository extends JpaRepository<BookCommentLike, Long> {
}
