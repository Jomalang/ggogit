package io.ggogit.ggogit.domain.book.repository;

import io.ggogit.ggogit.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByMember_Id(Long memberId);

    @Query("select b from Book b" +
            " where :query is null" +
            " or concat('b.',:filter) like concat('%', :query, '%')"
    )
    List<Book> findByQuery(String query, String filter);
}

