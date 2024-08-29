package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.BookLike;

import java.util.List;

public interface BookLikeRepository {
    List<BookLike> findAll();
}
