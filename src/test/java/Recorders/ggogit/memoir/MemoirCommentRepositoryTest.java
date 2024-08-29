package Recorders.ggogit.memoir;

import Recorders.ggogit.domain.memoir.entity.MemoirComment;
import Recorders.ggogit.domain.memoir.repository.MemoirCommentRepository;
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
public class MemoirCommentRepositoryTest {

    @Autowired
    private MemoirCommentRepository memoirCommentRepository;

    @Test
    public void saveTest() {
        MemoirComment testComment = createTestComment();
        memoirCommentRepository.save(testComment);

        MemoirComment foundComment = memoirCommentRepository.findById(testComment.getId());
        assertThat(foundComment.getContent()).isEqualTo("잘 읽었습니다~");

    }

    @Test
    public void deleteTest() {
        MemoirComment testComment = createTestComment();
        memoirCommentRepository.save(testComment);

        memoirCommentRepository.delete(testComment.getId());
        assertThat(memoirCommentRepository.findById(testComment.getId())).isNull();
    }

    @Test
    public void updateTest() {
        MemoirComment testComment = createTestComment();
        memoirCommentRepository.save(testComment);

        testComment.setContent("이건 별론데요...");
        memoirCommentRepository.update(testComment);
        MemoirComment foundComment = memoirCommentRepository.findById(testComment.getId());
        assertThat(foundComment.getContent()).isEqualTo("이건 별론데요...");
    }

    @Test
    public void findAllByMemoirIdTest() {
        for (int i = 0; i < 5; i++) {
            MemoirComment testComment = createTestComment();
            testComment.setContent("content" + i);
            memoirCommentRepository.save(testComment);
        }
        List<MemoirComment> commentList = memoirCommentRepository.findAllByMemoirId(2);
        assertThat(commentList.size()).isEqualTo(5);

        for (MemoirComment comment : commentList) {
            System.out.println(comment.getContent());
        }
    }

    @Test
    public void increasingLikeTest() {
        MemoirComment testComment = createTestComment();
        memoirCommentRepository.save(testComment);
        memoirCommentRepository.increaseLike(testComment.getId());
        memoirCommentRepository.increaseLike(testComment.getId());
        memoirCommentRepository.increaseLike(testComment.getId());

        assertThat(memoirCommentRepository.findById(testComment.getId()).getLikeCount()).isEqualTo(3);
    }

    private static MemoirComment createTestComment() {
        MemoirComment comment = new MemoirComment();
        comment.setMemberId(1);
        comment.setMemoirId(2);
        comment.setContent("잘 읽었습니다~");

        return comment;
    }

}
