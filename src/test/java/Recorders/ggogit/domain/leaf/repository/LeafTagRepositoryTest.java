package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@Rollback
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용
//@Sql("/domain/leaf/repository/leaf-tag-repository-test.sql")
class LeafTagRepositoryTest {

    @Autowired
    private LeafTagRepository leafTagRepository;

    @Test
    @DisplayName("저장 테스트 | LeafTag | save")
    void saveTest() {
        // given
        LeafTag leafTag = LeafTag.builder()
                .memberId(999L)
                .name("TagTest")
                .build();

        // when
        Long savedId = leafTagRepository.save(leafTag);

        // then
        assertThat(savedId).isNotNull();
    }

    @Test
    @DisplayName("조회 테스트 | LeafTag | findAll")
    void findAllTest() {
        //given
        Long memberId = 999L;
        Long offset = 0L;
        Long limit = 10L;

        // when
        List<LeafTag> leafTags = leafTagRepository.findAll(memberId, null, offset, limit);

        // then
        assertThat(leafTags.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("조회 테스트 | LeafTag | findById")
    void findByIdTest() {
        // given
        Long id = 1L;

        // when
        LeafTag leafTag = leafTagRepository.findById(id);

        // then
        assertThat(leafTag).isNotNull();
    }

    @Test
    @DisplayName("수정 테스트 | LeafTag | update")
    void updateTest() {
        // given
        Long id = 1L;
        LeafTag leafTag = LeafTag.builder()
                .id(id)
                .memberId(999L)
                .name("TagTest")
                .build();

        // when
        Long updatedCount = leafTagRepository.update(leafTag);

        // then
        assertThat(updatedCount).isEqualTo(1);
    }
}