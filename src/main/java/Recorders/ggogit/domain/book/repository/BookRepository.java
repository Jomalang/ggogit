package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BookRepository {
    void save(Book book);

    void update(Book book);

    List<Book> findAll();

    Book findByTitle(String title);

    void deleteById(Long id);

    void delete(Book book);
}
