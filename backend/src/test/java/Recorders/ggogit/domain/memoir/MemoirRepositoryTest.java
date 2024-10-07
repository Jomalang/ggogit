package Recorders.ggogit.domain.memoir;


import Recorders.ggogit.domain.memoir.entity.Memoir;
import Recorders.ggogit.domain.memoir.repository.MemoirRepository;
import Recorders.ggogit.domain.memoir.vIew.MemoirBookView;
import Recorders.ggogit.domain.memoir.vIew.MemoirCommentLikeView;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@Rollback
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        memoir.setTreeId(2L);
        memoirRepository.save(memoir);

        System.out.println("memoirId is : "+memoir.getId());
        Memoir foundMemoir = memoirRepository.findById(memoir.getTreeId());

        assertThat(foundMemoir.getTitle()).isEqualTo("testTitle1");

    }

    @Test
    public void UpdateTest() {
        Memoir memoir = memoirRepository.findByTitle("testTitle1");

        memoir.setTitle("newTitle");
        memoir.setText("newText");
        memoirRepository.update(memoir);

        Memoir foundMemoir = memoirRepository.findById(memoir.getTreeId());
        assertThat(foundMemoir.getText()).isEqualTo("newText");
    }

    @Test
    public void deleteTest() {
        Memoir memoir = memoirRepository.findByTitle("testTitle1");
        memoirRepository.delete(memoir.getTreeId());

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

    @Test
    public void MemoirBookTest() {
        List<MemoirBookView> memoirBookViews = memoirRepository.findMemoirBookViews(999L);
        for(MemoirBookView memoirBook : memoirBookViews){
        }
    }

    private static Memoir createTestMemoir() {
        Memoir memoir = new Memoir();
        memoir.setTreeId(1L);
        memoir.setTitle("testTitle1");
        memoir.setText("testText1");
        memoir.setVisibility(false);
        return memoir;
    }


}
