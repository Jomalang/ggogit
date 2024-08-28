package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTagMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeafTagMapRepository {
    void save(LeafTagMap leaf);
}