package Recorders.ggogit.memoir;

import Recorders.ggogit.domain.memoir.Memoir;
import Recorders.ggogit.domain.memoir.MemoirRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Transactional
@SpringBootTest
public class MemoirRepositoryTest {

    @Autowired
    private MemoirRepository memoirRepository;

    @Test
    public void findAllTest() {
        List<Memoir> memoirList = memoirRepository.findAll();
        Assertions.assertThat(memoirList.get(0).getId()).isEqualTo(3L);

    }
}
