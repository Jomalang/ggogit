package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

import java.util.List;

@DataJpaTest
@Profile("test")
class LeafBookRepositoryTest {

    @Autowired
    private LeafBookRepository leafBookRepository;

    @Test
    @DisplayName("Entity Test | leafBookRepository.findByLeaf_Tree_Id")
    public void findByLeaf_Tree_Id() {
        // given
        long treeId = 1L;

        // when
//        List<LeafBook> leafBooks =  leafBookRepository.findByLeaf_Tree_Id(treeId);

        // then
//        for (LeafBook leafBook : leafBooks) {
//            System.out.println(leafBooks);
//        }
    }
}