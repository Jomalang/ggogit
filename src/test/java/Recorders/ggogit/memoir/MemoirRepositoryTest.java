package Recorders.ggogit.memoir;

import Recorders.ggogit.domain.memoir.entity.Memoir;
import Recorders.ggogit.domain.memoir.repository.MemoirRepository;
import Recorders.ggogit.domain.memoir.vIew.MemoirCommentLikeView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class MemoirRepositoryTest {

    @Autowired
    private MemoirRepository memoirRepository;

    @BeforeEach
    public void beforeEach() {
        Memoir testMemoir = createTestMemoir();
        memoirRepository.save(testMemoir);
    }

    @Test
    public void findAllTest() {
        List<Memoir> memoirList = memoirRepository.findAll();
        System.out.println(memoirList);
        assertThat(memoirList).isNotEmpty();
    }

    @Test
    public void saveTest() {
        Memoir memoir = createTestMemoir();
        memoirRepository.save(memoir);

        Memoir foundMemoir = memoirRepository.findById(memoir.getId());
        assertThat(foundMemoir.getTitle()).isEqualTo("testTitle1");


    }

    @Test
    public void UpdateTest() {
        Memoir memoir = memoirRepository.findByTitle("testTitle1");

        memoir.setTitle("newTitle");
        memoir.setText("newText");
        memoirRepository.update(memoir);

        Memoir foundMemoir = memoirRepository.findById(memoir.getId());
        assertThat(foundMemoir.getText()).isEqualTo("newText");
    }

    @Test
    public void deleteTest() {
        Memoir memoir = memoirRepository.findByTitle("testTitle1");
        memoirRepository.delete(memoir.getId());

        List<Memoir> memoirList = memoirRepository.findAll();
        assertThat(memoirList.size()).isEqualTo(1);
    }

    @Test
    public void obtainCntTest() {
        Memoir testMemoir = createTestMemoir();
        memoirRepository.save(testMemoir);

        MemoirCommentLikeView memoirCntView = memoirRepository.findCntById(testMemoir.getId());
        assertThat(memoirCntView.getCommentCnt()).isEqualTo(0);
        assertThat(memoirCntView.getLikeCnt()).isEqualTo(0);
    }

    private static Memoir createTestMemoir() {
        Memoir memoir = new Memoir();
        memoir.setTreeId(2);
        memoir.setTitle("testTitle1");
        memoir.setText("testText1");
        memoir.setVisibility(false);
        return memoir;
    }


}
