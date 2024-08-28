package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeafTagRepository {
    void save(LeafTag leaf);
}