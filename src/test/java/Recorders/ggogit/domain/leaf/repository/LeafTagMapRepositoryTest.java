package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTagMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;


@MybatisTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용
@Sql("/domain/leaf/repository/leaf-tag-map-repository-test.sql")
class LeafTagMapRepositoryTest {

    @Autowired
    private LeafTagMapRepository leafTagMapRepository;

    @Test
    @DisplayName("저장 테스트 | LeafTagMap | save")
    void save() {
        // given
        // 9990L이라는 leafId와 1L이라는 tagId를 가진 LeafTagMap이 존재함 (leaf-tag-map-repository-test.sql 참고)
        Long leafId = 9990L;
        Long tagId = 4L;

        LeafTagMap leafTagMap = LeafTagMap.builder()
                .leafId(leafId)
                .leafTagId(tagId)
                .build();

        // when
        leafTagMapRepository.save(leafTagMap);

        // given
        LeafTagMap savedLeafTagMap = leafTagMapRepository.findByLeafIdAndLeafTagId(leafId, tagId);
        assertThat(savedLeafTagMap).isNotNull();
    }

    @Test
    @DisplayName("조회 테스트 | LeafTagMap | findByLeafIdAndLeafTagId")
    void findByLeafIdAndLeafTagId() {
        // given
        // 9990L이라는 leafId와 1L이라는 tagId를 가진 LeafTagMap이 존재함 (leaf-tag-map-repository-test.sql 참고)
        Long leafId = 9990L;
        Long leafTagId = 3L;

        // when
        leafTagMapRepository.findByLeafIdAndLeafTagId(leafId, leafTagId);
    }

    @Test
    @DisplayName("조회 테스트 | LeafTagMap | findByLeafId")
    void findByLeafId() {
        // given
        // 9990L이라는 leafId를 가진 LeafTagMap이 존재함 (leaf-tag-map-repository-test.sql 참고)
        Long leafId = 9990L;

        // when
        LeafTagMap leafTagMap = leafTagMapRepository.findByLeafId(leafId).getFirst();

        // then
        assertThat(leafTagMap).isNotNull();
    }

    @Test
    @DisplayName("조회 테스트 | LeafTagMap | findByTagId")
    void findByTagId() {
        // given
        // 1L이라는 tagId를 가진 LeafTagMap이 존재함 (leaf-tag-map-repository-test.sql 참고)
        Long tagId = 1L;

        // when
        LeafTagMap leafTagMap = leafTagMapRepository.findByLeafTagId(tagId).getFirst();

        // then
        assertThat(leafTagMap).isNotNull();
    }
}