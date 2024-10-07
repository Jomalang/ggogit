package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafLike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@MybatisTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용
@Sql("/domain/leaf/repository/leaf-like-repository-test.sql")
class LeafLikeRepositoryTest {

    @Autowired
    private LeafLikeRepository leafLikeRepository;

    @Test
    @DisplayName("저장 테스트 | LeafLike | save")
    void save() {
        // given
        LeafLike leafLike = LeafLike.builder()
                .memberId(888L)
                .leafId(8880L)
                .build();

        // when
        Long savedId = leafLikeRepository.save(leafLike);

        // then
        assertNotNull(savedId);
    }

    @Test
    @DisplayName("조회 테스트 | LeafLike | findByMemberId")
    void findByMemberId() {
        // given
        // 888L이라는 memberId를 가진 LeafLike가 존재함 (leaf-like-repository-test.sql 참고)

        // when
        Long memberId = 888L;
        LeafLike leafLike = leafLikeRepository.findByMemberId(memberId).getFirst();

        // then
        assertNotNull(leafLike);
    }

    @Test
    @DisplayName("조회 테스트 | LeafLike | findByLeafId")
    void findByLeafId() {
        // given
        // 8880L이라는 leafId를 가진 LeafLike가 존재함 (leaf-like-repository-test.sql 참고)

        // when
        Long leafId = 8881L;
        LeafLike leafLike = leafLikeRepository.findByLeafId(leafId).getFirst();

        // then
        assertNotNull(leafLike);
    }

    @Test
    @DisplayName("조회 테스트 | LeafLike | findAll")
    void findAll() {
        // given

        // when
        List<LeafLike> leafLikes = leafLikeRepository.findAll();

        // then
        assertNotNull(leafLikes);
    }

    @Test
    @DisplayName("수정 테스트 | LeafLike | update")
    void update() {
        // given
        // 888L이라는 memberId를 가진 LeafLike가 존재함 (leaf-like-repository-test.sql 참고)
        LeafLike leafLike = LeafLike.builder()
                .memberId(888L)
                .leafId(8881L)
                .activate(false)
                .build();

        // when
        Long updatedId = leafLikeRepository.update(leafLike);

        // then
        LeafLike updateLeafLike = leafLikeRepository.findByMemberIdAndLeafId(888L, 8881L);
        assertFalse(updateLeafLike.isActivate());
    }
}