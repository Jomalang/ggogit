package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Profile("test")
class LeafRepositoryTest {

    @Autowired
    private LeafRepository leafRepository;

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


}