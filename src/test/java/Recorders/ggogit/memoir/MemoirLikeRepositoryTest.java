package Recorders.ggogit.memoir;

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
        boolean before = foundMemoirLike.isActivate();

        memoirLikeRepository.toggle(memoirLike);
        MemoirLike foundMemoirLike2 = memoirLikeRepository.findById(memoirLike);
        boolean after = foundMemoirLike2.isActivate();

        assertThat(after).isFalse();
    }

    private static MemoirLike createMemoirLike() {
        MemoirLike memoirLike = new MemoirLike();
        memoirLike.setMemoirId(2);
        memoirLike.setMemberId(1);

        return memoirLike;
    }
}
