package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.repository.filter.LeafRepositoryFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeafRepositoryTest {

    @Autowired
    private LeafRepository leafRepository;

    @Test
    @DisplayName("저장 테스트 | Leaf | save")
    void save() {
        // given
        Leaf leaf = Leaf.builder()
                .treeId(1L)
                .parentLeafId(null)
                .visibility(true)
                .viewCount(null)
                .likeCount(null)
                .title("TitleTest")
                .content("ContentTest")
                .childLeafCount(null)
                .bookMark(null)
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
        Leaf leaf = leafRepository.findById(9970L);

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
        List<Leaf> leafs = leafRepository.findAll();

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
        leaf.setLikeCount(1L);
        leaf.setViewCount(1L);
        leaf.setChildLeafCount(1L);
        leaf.setBookMark(1L);

        // when
        leafRepository.update(leaf);

        // then
        Leaf updatedLeaf = leafRepository.findById(9971L);
        assertEquals(updatedLeaf.getTitle(), "UpdatedTitle");
        assertEquals(updatedLeaf.getContent(), "UpdatedContent");
        assertFalse(updatedLeaf.getVisibility());
        assertEquals(updatedLeaf.getLikeCount(), 1);
        assertEquals(updatedLeaf.getViewCount(), 1);
        assertEquals(updatedLeaf.getChildLeafCount(), 1);
        assertEquals(updatedLeaf.getBookMark(), 1L);
    }

    @Test
    void findByTreeIdOrderByCreateTimeDesc() {
        // given
        List<Leaf> leafs = leafRepository.findByTreeIdOrderByCreateTimeDesc(1L);
        for (Leaf leaf : leafs) {
            System.out.println(leaf);
        }
        // when

        // then
    }
}