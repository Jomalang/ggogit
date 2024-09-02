package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafComment;
import Recorders.ggogit.domain.leaf.entity.LeafCommentLike;
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
@Sql("/domain/leaf/repository/leaf-comment-like-repository-test.sql")
class LeafCommentLikeRepositoryTest {

    @Autowired
    private LeafCommentLikeRepository leafCommentLikeRepository;

    @Test
    @DisplayName("저장 테스트 | LeafCommentLike | save")
    void save() {
        // given
        LeafCommentLike leafCommentLike = LeafCommentLike.builder()
                .memberId(557L)
                .leafCommentId(557L)
                .build();

        // when
        Long savedId = leafCommentLikeRepository.save(leafCommentLike);

        // then
        assertNotNull(savedId);
    }

    @Test
    @DisplayName("조회 테스트 | LeafCommentLike | findByLeafCommentIdAndMemberId")
    void findByLeafCommentIdAndMemberId() {
        // given
        // when
        LeafCommentLike leafCommentLike = leafCommentLikeRepository.findByLeafCommentIdAndMemberId(558L, 557L);
        // then
        assertNotNull(leafCommentLike);
    }

    @Test
    @DisplayName("조회 테스트 | LeafCommentLike | findByLeafCommentId")
    void findByLeafCommentId() {
        // given
        // when
        LeafCommentLike leafCommentLike = leafCommentLikeRepository.findByLeafCommentId(558L).getFirst();
        // then
        assertNotNull(leafCommentLike);
    }

    @Test
    @DisplayName("조회 테스트 | LeafCommentLike | findByMemberId")
    void findByMemberId() {
        // given
        // when
        LeafCommentLike leafCommentLike = leafCommentLikeRepository.findByMemberId(557L).getFirst();
        // then
        assertNotNull(leafCommentLike);
    }

    @Test
    @DisplayName("수정 테스트 | LeafCommentLike | update")
    void update() {
        // given
        LeafCommentLike leafCommentLike = LeafCommentLike.builder()
                .memberId(557L)
                .leafCommentId(558L)
                .activate(false)
                .build();

        // when
        Integer result = leafCommentLikeRepository.update(leafCommentLike);

        // then
        assertEquals(1, result);
    }
}