package Recorders.ggogit.domain.memoir.repository;

import Recorders.ggogit.domain.memoir.entity.MemoirCommentLike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemoirCommentLikeRepository {

    void save(MemoirCommentLike memoirCommentLike);

    void toggle(MemoirCommentLike memoirCommentLike);

    /**
     * @param memoirCommentLike
     * @return MemoirLike
     * 테스트 위한 메서드입니다. 리스트를 반환하는건 MemoirRepository에 구현되어있습니다.
     */
    MemoirCommentLike findById(MemoirCommentLike memoirCommentLike);
}
