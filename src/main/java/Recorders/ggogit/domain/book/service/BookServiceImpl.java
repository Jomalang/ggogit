package Recorders.ggogit.domain.book.service;

import Recorders.ggogit.domain.book.entity.Book;
import Recorders.ggogit.domain.book.repository.BookRepository;
import Recorders.ggogit.domain.book.view.BookPreviewView;
import Recorders.ggogit.domain.book.view.BookDetailView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public Long register(Book book) {
        bookRepository.save(book);
        return book.getId();
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

    @Override
    public List<BookPreviewView> getBooksbyTitle(String title) {
        List<BookPreviewView> books = bookRepository.findBookCategoryViewByTitle(title);
        return books;
    }

    @Override
    public List<BookPreviewView> getBooksbyAuthor(String author) {
        List<BookPreviewView> books = bookRepository.findBookCategoryViewByAuthor(author);
        return books;
    }
}
