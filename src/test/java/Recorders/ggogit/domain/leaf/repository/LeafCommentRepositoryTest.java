package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafComment;
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
@Sql("/domain/leaf/repository/leaf-comment-repository-test.sql")
class LeafCommentRepositoryTest {

    @Autowired
    private LeafCommentRepository leafCommentRepository;

    @Test
    @DisplayName("저장 테스트 | LeafComment | save")
    void save() {
        // given
        LeafComment leafComment = LeafComment.builder()
                .leafId(6670L)
                .memberId(667L)
                .content("댓글 내용")
                .build();

        // when
        Long savedLeafId = leafCommentRepository.save(leafComment);

        // then
        assertNotNull(savedLeafId);
    }

    @Test
    @DisplayName("조회 테스트 | LeafComment | findById")
    void findById() {
        // given
        // 667L 이라는 id를 가진 리프 댓글이 존재 합니다.

        // when
        LeafComment leafComment = leafCommentRepository.findById(667L);

        // then
        assertNotNull(leafComment);
    }

    @Test
    @DisplayName("조회 테스트 | LeafComment | findByLeafId")
    void findByLeafId() {
        // given
        // when
        LeafComment leafComment = leafCommentRepository.findByLeafId(6670L).getFirst();

        // then
        assertNotNull(leafComment);
    }

    @Test
    @DisplayName("조회 테스트 | LeafComment | findByMemberId")
    void findByMemberId() {
        // given
        // when
        LeafComment leafComment = leafCommentRepository.findByMemberId(667L).getFirst();

        // then
        assertNotNull(leafComment);
    }

    @Test
    @DisplayName("조회 테스트 | LeafComment | findAll")
    void findAll() {
        // given
        // when
        LeafComment leafComment = leafCommentRepository.findAll().getFirst();
        // then
        assertNotNull(leafComment);
    }

    @Test
    @DisplayName("수정 테스트 | LeafComment | update")
    void update() {
        // given
        LeafComment leafComment = LeafComment.builder()
                .id(667L)
                .likeCount(1L)
                .content("수정된 댓글 내용")
                .build();

        // when
        leafCommentRepository.update(leafComment);

        // then
        LeafComment updatedLeafComment = leafCommentRepository.findById(667L);
        assertEquals(1L, updatedLeafComment.getLikeCount());
        assertEquals("수정된 댓글 내용", updatedLeafComment.getContent());
    }
}