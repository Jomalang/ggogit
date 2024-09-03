package Recorders.ggogit.domain.tree.repository;

import Recorders.ggogit.domain.tree.entity.TreeLike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TreeLikeRepository {
    void save(TreeLike treeLike);
    TreeLike findById(Long memberId, Long treeId);
    void update(TreeLike treeLike);
    void delete(Long memberId, Long treeId);

}
