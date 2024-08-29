package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.BookCommentLike;

import java.util.List;

public interface BookCommentLikeRepository {
    List<BookCommentLike> findAll();
}
