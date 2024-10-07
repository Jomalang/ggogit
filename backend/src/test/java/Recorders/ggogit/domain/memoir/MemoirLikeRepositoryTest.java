package Recorders.ggogit.domain.memoir;

import Recorders.ggogit.domain.memoir.entity.MemoirLike;
import Recorders.ggogit.domain.memoir.repository.MemoirLikeRepository;
import Recorders.ggogit.domain.memoir.repository.MemoirRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class MemoirLikeRepositoryTest {

    @Autowired
    MemoirLikeRepository memoirLikeRepository;
    @Autowired
    private MemoirRepository memoirRepository;

    @Test
    public void saveTest() {
        MemoirLike memoirLike = createMemoirLike();
        memoirLikeRepository.save(memoirLike);

        assertThat(memoirLike.getMemoirId()).isEqualTo(2);

    }

    @Test
    public void toggleTest() {
        MemoirLike memoirLike = createMemoirLike();
        memoirLikeRepository.save(memoirLike);
        MemoirLike foundMemoirLike = memoirLikeRepository.findById(memoirLike);
        boolean before = foundMemoirLike.getActivate();

        memoirLikeRepository.toggle(memoirLike);
        MemoirLike foundMemoirLike2 = memoirLikeRepository.findById(memoirLike);
        boolean after = foundMemoirLike2.getActivate();

        assertThat(after).isFalse();
    }

    private static MemoirLike createMemoirLike() {
        MemoirLike memoirLike = new MemoirLike();
        memoirLike.setMemoirId(2L);
        memoirLike.setMemberId(1L);

        return memoirLike;
    }
}
