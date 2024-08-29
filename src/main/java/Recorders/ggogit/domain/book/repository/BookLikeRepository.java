package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.BookLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookLikeRepository {

    void save(BookLike bookLike);

    List<BookLike> findAll();
}
