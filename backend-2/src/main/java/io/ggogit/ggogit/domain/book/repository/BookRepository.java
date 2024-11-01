package io.ggogit.ggogit.domain.book.repository;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.repository.query.BookQueryRepository;
import io.ggogit.ggogit.domain.member.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, BookQueryRepository {
    List<Book> findByMember_Id(Long memberId);

    @Query("select b from Book b"+
            " where b.title is null or b.title like concat('%', :query, '%')"
    )

    List<Book> findByTitle(@Param("query") String query, Pageable pageable);
    @Query("select b from Book b"+
            " where b.title is null or b.title like concat('%', :query, '%')"
    )

    List<Book> findByAuthor(@Param("query") String query, Pageable pageable);
    @Query("select b from Book b"+
            " where b.publisher is null or b.publisher like concat('%', :query, '%')"
    )
    List<Book> findByPublisher(@Param("query") String query, Pageable pageable);

    boolean existsByIdAndMember(Long bookId, Member member);
}

