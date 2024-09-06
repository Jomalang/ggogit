package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.BookCommentLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookCommentLikeRepository {

    void save(BookCommentLike bookCommentLike);

    void update(BookCommentLike bookCommentLike);

    List<BookCommentLike> findAll();
}