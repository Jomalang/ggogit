package Recorders.ggogit.domain.memoir.repository;

import Recorders.ggogit.domain.memoir.entity.MemoirComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoirCommentRepository {
    int save(MemoirComment comment);

    long delete(long id);

    int update(MemoirComment comment);

    List<MemoirComment> findAllByMemoirId(long memoirId);

    void increaseLike(long id);

    MemoirComment findById(long id);
}