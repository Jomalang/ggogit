package Recorders.ggogit.domain.memoir.repository;

import Recorders.ggogit.domain.memoir.entity.MemoirLike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemoirLikeRepository {
    void save(MemoirLike memoirLike);

    void toggle(MemoirLike memoirLike);

    /**
     * @param memoirLike
     * @return MemoirLike
     * 테스트 위한 메서드입니다. 리스트를 반환하는건 MemoirRepository에 구현되어있습니다.
     */
    MemoirLike findById(MemoirLike memoirLike);

}
