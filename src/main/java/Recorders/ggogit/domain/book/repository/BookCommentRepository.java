package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.BookComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookCommentRepository {
    List<BookComment> findAll();
}
