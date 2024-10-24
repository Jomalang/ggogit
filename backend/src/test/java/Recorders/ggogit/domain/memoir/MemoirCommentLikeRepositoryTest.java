package Recorders.ggogit.domain.memoir;

import Recorders.ggogit.domain.memoir.entity.MemoirCommentLike;
import Recorders.ggogit.domain.memoir.entity.MemoirLike;
import Recorders.ggogit.domain.memoir.repository.MemoirCommentLikeRepository;
import Recorders.ggogit.domain.memoir.repository.MemoirLikeRepository;
import Recorders.ggogit.domain.memoir.repository.MemoirRepository;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class MemoirCommentLikeRepositoryTest {

    @Autowired
    MemoirCommentLikeRepository memoirCommentLikeRepository;

    @Test
    public void saveTest() {
        MemoirCommentLike memoirCommentLike = createMemoirCommentLike();
        memoirCommentLikeRepository.save(memoirCommentLike);

        assertThat(memoirCommentLike.getMemoirCommentId()).isEqualTo(31);

    }

    @Test
    public void toggleTest() {
        MemoirCommentLike memoirCommentLike = createMemoirCommentLike();
        memoirCommentLikeRepository.save(memoirCommentLike);
        MemoirCommentLike foundMemoirCommentLike = memoirCommentLikeRepository.findById(memoirCommentLike);
        boolean before = foundMemoirCommentLike.getActivate();

        memoirCommentLikeRepository.toggle(memoirCommentLike);
        MemoirCommentLike foundMemoirLike2 = memoirCommentLikeRepository.findById(memoirCommentLike);
        boolean after = foundMemoirLike2.getActivate();

        assertThat(after).isFalse();
    }

    private static MemoirCommentLike createMemoirCommentLike() {
        MemoirCommentLike memoirCommentLike = new MemoirCommentLike();
        memoirCommentLike.setMemoirCommentId(31L);
        memoirCommentLike.setMemberId(1L);

        return memoirCommentLike;
    }
}
