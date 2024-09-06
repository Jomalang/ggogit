package Recorders.ggogit.domain.book.service;

import Recorders.ggogit.domain.book.entity.Book;
import Recorders.ggogit.domain.book.repository.BookRepository;
import Recorders.ggogit.domain.book.view.BookDetailView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Long register(Book book) {
        return bookRepository.save();
    }

    @Override
    public BookDetailView get(Long bookId) {
        return null;
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
}
