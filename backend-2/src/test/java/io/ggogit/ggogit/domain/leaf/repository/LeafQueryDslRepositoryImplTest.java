package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;


@Profile("test")
@SpringBootTest
class LeafQueryDslRepositoryImplTest {

    @Autowired
    private LeafQueryDslRepositoryImpl leafQueryDslRepository;


    @Test
    @DisplayName("Test findByLeafId")
    void findByLeafId() {
        // given
        Long leafId = 1L;

        Leaf actualLeaf = leafQueryDslRepository.findByLeafId(leafId)
                .orElse(null);

        // then
        assertNotNull(actualLeaf);
    }

    @Test
    @DisplayName("테스트 이름")
    void getBookCards() {
        // given

        // when
        Page<Leaf> actualPage = leafQueryDslRepository.getBookCards(777L, 1, 10);

        for (Leaf leaf : actualPage.getContent()) {
            System.out.println("leaf = " + leaf);
        }

        // then
    }
}