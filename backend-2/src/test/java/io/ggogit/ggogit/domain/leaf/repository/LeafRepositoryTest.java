package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Profile("test")
class LeafRepositoryTest {

    @Autowired
    private LeafRepository leafRepository;
    @Autowired
    private TreeRepository treeRepository;

    @Test
    @DisplayName("Entity Test | LeafRepository.findById")
    public void findById() {
        // given
        Long leafId = 1L;

        // when
        Leaf leaf = leafRepository.findById(leafId)
                .orElse(null);

        // then
        assertNotNull(leaf);
        assertEquals(leafId, leaf.getId());
    }

    @Test
    @DisplayName("Entity Test | LeafRepository.findByTree")
    public void findByTree() {
        // given
        Long treeId = 3000L;
        Tree tree = treeRepository.findById(treeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 트리가 존재하지 않습니다."));
        System.out.println("tree = " + tree);
        // when
        List<Leaf> leaves = leafRepository.findByTree(tree);

        for (Leaf leaf : leaves) {
            System.out.println("leaf = " + leaf);
        }

        assertThat(leaves.size()).isGreaterThan(0);
    }
}