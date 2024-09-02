package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용
@Sql("/domain/leaf/repository/leaf-book-repository-test.sql")
class LeafBookRepositoryTest {

    @Autowired
    private LeafBookRepository leafBookRepository;

    @Test
    @DisplayName("저장 테스트 | LeafBook | save")
    void save() {
        // given
        LeafBook leafBook = LeafBook.builder()
                .leafId(7771L)
                .startPage(1L)
                .endPage(10L)
                .build();

        // when
        Long savedLeafId = leafBookRepository.save(leafBook);

        // then
        assertNotNull(savedLeafId);
    }

    @Test
    @DisplayName("조회 테스트 | LeafBook | findByLeafId")
    void findByLeafId() {
        // given
        Long leafId = 7770L;

        // when
        LeafBook leafBook = leafBookRepository.findByLeafId(leafId);

        // then
        assertNotNull(leafBook);
    }

    @Test
    @DisplayName("수정 테스트 | LeafBook | update")
    void update() {
        // given
        LeafBook leafBook = LeafBook.builder()
                .leafId(7770L)
                .startPage(1L)
                .endPage(11L)
                .build();

        // when
        Long updatedLeafId = leafBookRepository.update(leafBook);

        // then
        assertNotNull(updatedLeafId);
    }
}