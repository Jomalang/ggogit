package io.ggogit.ggogit.domain.book.service;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.book.repository.BookCategoryRepository;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;

    @Override
    public Long register(Book book) {
        return 0;
    }

    @Override
    public List<Book> gets() {
        return List.of();
    }

    @Override
    public void modify(Book book) {

    }

    @Override
    public void remove(Long bookId) {

    }

    @Override
    public BookCategory getBookCategory(Long bookId) {
        return bookCategoryRepository.findById(bookId).orElse(null);
    }
}
