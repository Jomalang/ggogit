package io.ggogit.ggogit.domain.book.repository.query;

import io.ggogit.ggogit.domain.book.entity.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookQueryRepository {
    List<Book> findByFilter(String filter, String query, Pageable pageable);
}
