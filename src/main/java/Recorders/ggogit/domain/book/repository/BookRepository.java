package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
}
