package Recorders.ggogit.api.book;

import Recorders.ggogit.domain.book.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AladinRepository {

    void save(Book book);

    boolean existsByIsbn(String isbn);

}
