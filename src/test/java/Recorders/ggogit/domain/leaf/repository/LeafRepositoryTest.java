package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.repository.filter.LeafRepositoryFilter;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
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
@Sql("/domain/leaf/repository/leaf-repository-test.sql")
class LeafRepositoryTest {

    @Autowired
    private LeafRepository leafRepository;

    @Test
    @DisplayName("저장 테스트 | Leaf | save")
    void save() {
        // given
        Leaf leaf = Leaf.builder()
                .treeId(997L)
                .parentLeafId(997L)
                .visibility(true)
                .viewCount(0)
                .likeCount(0)
                .title("TitleTest")
                .content("ContentTest")
                .childLeafCount(0)
                .bookMark(0L)
                .build();

        // when
        Long savedId = leafRepository.save(leaf);

        // then
        assertNotNull(savedId);
    }

    @Test
    @DisplayName("조회 테스트 | Leaf | findById")
    void findById() {
        // given
        // 977L이라는 id를 가진 Leaf가 존재함 (leaf-repository-test.sql 참고)

        // when
        Leaf leaf = leafRepository.findById(997L);

        // then
        System.out.println(leaf);
        assertNotNull(leaf);
    }

    @Test
    @DisplayName("조회 테스트 | Leaf | findAll")
    void findAll() {
        // given
        LeafRepositoryFilter filter = new LeafRepositoryFilter();
        filter.addTreeId(997L);

        // when
        List<Leaf> leafs = leafRepository.findAll(filter);

        // then
        System.out.println(leafs);
        System.out.println(leafs.size());
    }

    @Test
    @DisplayName("수정 테스트 | Leaf | update")
    void update() {
        // given
        Leaf leaf = leafRepository.findById(9971L);
        leaf.setTitle("UpdatedTitle");
        leaf.setContent("UpdatedContent");
        leaf.setVisibility(false);
        leaf.setLikeCount(1);
        leaf.setViewCount(1);
        leaf.setChildLeafCount(1);
        leaf.setBookMark(1L);

        // when
        Long updatedId = leafRepository.update(leaf);

        // then
        Leaf updatedLeaf = leafRepository.findById(updatedId);
        assertEquals(updatedLeaf.getTitle(), "UpdatedTitle");
        assertEquals(updatedLeaf.getContent(), "UpdatedContent");
        assertFalse(updatedLeaf.getVisibility());
        assertEquals(updatedLeaf.getLikeCount(), 1);
        assertEquals(updatedLeaf.getViewCount(), 1);
        assertEquals(updatedLeaf.getChildLeafCount(), 1);
        assertEquals(updatedLeaf.getBookMark(), 1L);
    }
}