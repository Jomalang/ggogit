package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafLikeRepository {
    Long save(LeafLike leafLike);
    List<LeafLike> findByLeafId(Long leafId);
    List<LeafLike> findByMemberId(Long memberId);
    List<LeafLike> findAll();
    Long update(LeafLike leafLike);
}