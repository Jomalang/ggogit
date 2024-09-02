package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTagMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeafTagMapRepository {
    Long save(LeafTagMap leaf);
    Long update(LeafTagMap leaf);
}