package io.ggogit.ggogit.domain.book.repository;

import io.ggogit.ggogit.domain.book.entity.BookLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLikeRepository extends JpaRepository<BookLike, Long> {
}
