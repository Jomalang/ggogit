package io.ggogit.ggogit.domain.book.service;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Long register(Book book) {
        return 0L;
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
    public Book findById(Long bookId) {
        Book book =
        bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다."));
        return book;
    }
}
