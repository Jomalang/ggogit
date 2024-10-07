package Recorders.ggogit.domain.tree.repository;

import Recorders.ggogit.domain.tree.entity.Seed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeedRepositoryTest {

    @Autowired
    SeedRepository seedRepository;

    @Test
    void findById() {
        // given
        // when
        Seed seed = seedRepository.findById(1L);
        System.out.println(seed);

        // then
    }
}