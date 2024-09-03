package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.BookLike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

@MybatisTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookLikeRepositoryTest {

    @Autowired
    private BookLikeRepository bookLikeRepository;

    @Test
    @DisplayName("도서 좋아요 저장 테스트")
    void save() {
        // given 주어진 것
        BookLike bookLike = BookLike.builder()
                .memberId(1L)
                .bookId(1L)
                .activate(true)
                .updateTime(null)
                .createTime(null)
                .build();

        // when 실행했을 때
        bookLikeRepository.save(bookLike);

        // then 결과

    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }
}