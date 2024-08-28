package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafCommentRepository {
    void save(LeafComment leafComment);
}