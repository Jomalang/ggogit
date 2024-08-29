package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookRepository {

    void save(Book book);

    List<Book> findAll();
}
