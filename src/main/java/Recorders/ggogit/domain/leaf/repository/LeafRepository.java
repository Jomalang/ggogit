package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeafRepository {
    void save(Leaf leaf);
}