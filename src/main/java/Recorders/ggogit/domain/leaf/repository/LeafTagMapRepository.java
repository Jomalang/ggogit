package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTagMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafTagMapRepository {
    Long save(LeafTagMap leaf);
    LeafTagMap findByLeafIdAndLeafTagId(Long leafId, Long leafTagId);
    List<LeafTagMap> findByLeafId(Long leafId);
    List<LeafTagMap> findByLeafTagId(Long tagId);
}