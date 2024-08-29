package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.BookCommentLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookCommentLikeRepository {
    List<BookCommentLike> findAll();
}
