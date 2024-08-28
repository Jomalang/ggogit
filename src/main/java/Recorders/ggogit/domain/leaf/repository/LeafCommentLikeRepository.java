package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafCommentLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafCommentLikeRepository {
    void save(LeafCommentLike leafCommentLike);
}