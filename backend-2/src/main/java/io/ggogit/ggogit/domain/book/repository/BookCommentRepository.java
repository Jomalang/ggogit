package io.ggogit.ggogit.domain.book.repository;

import io.ggogit.ggogit.domain.book.entity.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {
}
