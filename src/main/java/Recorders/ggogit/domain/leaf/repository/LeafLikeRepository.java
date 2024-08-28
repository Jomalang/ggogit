package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafLikeRepository {
    void save(LeafLike leafLike);
}