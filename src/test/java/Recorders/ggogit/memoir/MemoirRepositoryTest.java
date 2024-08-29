package Recorders.ggogit.memoir;

import Recorders.ggogit.domain.memoir.Memoir;
import Recorders.ggogit.domain.memoir.MemoirRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//@Transactional
//@MybatisTest
//@Rollback

@MybatisTest
@Rollback
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemoirRepositoryTest {

    @Autowired
    private MemoirRepository memoirRepository;

    @Test
    public void findAllTest() {
        List<Memoir> memoirList = memoirRepository.findAll();
        System.out.println(memoirList);
        assertThat(memoirList.get(0).getId()).isEqualTo(3L);
    }

    @Test
    public void saveTest() {
        Memoir memoir = createTestMemoir();
        memoirRepository.save(memoir);

        Memoir testTitle1 = memoirRepository.findByTitle("testTitle1");
        assertThat(testTitle1.getTreeId()).isEqualTo(4);
    }

    @Test
    public void UpdateTest() {
        Memoir memoir = memoirRepository.findById(3);

        memoir.setTitle("newTitle");
        memoir.setText("newText");
        memoirRepository.update(memoir);

        Memoir foundMemoir = memoirRepository.findByTitle("newTitle");
        assertThat(foundMemoir.getText()).isEqualTo("newText");
    }

    @Test
    public void deleteTest() {
        Memoir memoir = memoirRepository.findById(3);
        memoirRepository.delete(memoir.getId());

        List<Memoir> memoirList = memoirRepository.findAll();
        assertThat(memoirList).isEmpty();
    }

    private static Memoir createTestMemoir() {
        Memoir memoir = new Memoir();
        memoir.setTreeId(4);
        memoir.setTitle("testTitle1");
        memoir.setText("testText1");
        memoir.setVisibility(false);
        return memoir;
    }


}
