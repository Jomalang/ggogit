package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.BookComment;

import java.util.List;

public interface BookCommentRepository {
    List<BookComment> findAll();
}
