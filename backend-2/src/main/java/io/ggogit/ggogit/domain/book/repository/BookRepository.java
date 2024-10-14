package io.ggogit.ggogit.domain.book.repository;

import io.ggogit.ggogit.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
