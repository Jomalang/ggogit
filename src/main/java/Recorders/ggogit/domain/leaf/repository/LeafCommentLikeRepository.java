package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafCommentLike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeafCommentLikeRepository {
    Long save(LeafCommentLike leafCommentLike);
    Integer update(LeafCommentLike leafCommentLike);
}