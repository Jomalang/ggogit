package io.ggogit.ggogit.domain.book.repository;

import io.ggogit.ggogit.domain.book.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
}
