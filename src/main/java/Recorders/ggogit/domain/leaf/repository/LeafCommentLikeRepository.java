package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafCommentLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafCommentLikeRepository {
    Long save(LeafCommentLike leafCommentLike);
    LeafCommentLike findByLeafCommentIdAndMemberId(Long leafCommentId, Long memberId);
    List<LeafCommentLike> findByLeafCommentId(Long leafCommentId);
    List<LeafCommentLike> findByMemberId(Long memberId);
    Integer update(LeafCommentLike leafCommentLike);
}