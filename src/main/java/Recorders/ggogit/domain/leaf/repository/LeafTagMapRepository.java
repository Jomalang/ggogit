package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTagMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafTagMapRepository {

    /**
     * 리프 태그 맵핑 저장
     * @param leaf 리프 태그 맵핑
     * @return 저장된 ID
     */
    Long save(LeafTagMap leaf);
    LeafTagMap findByPk(Long leafId, Long leafTagId);
    List<LeafTagMap> findByLeafId(Long leafId);
    List<LeafTagMap> findByLeafTagId(Long tagId);
    void delete(LeafTagMap leafTagMap);
}