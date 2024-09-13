package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafTagRepository {
    Long save(LeafTag leafTag);
    LeafTag findById(Long id);
    List<LeafTag> findAll(Long memberId, String name, Long offset, Long limit);
    Long count(Long memberId, String name);
    Long update(LeafTag leafTag);
    Boolean existsById(Long leafTagId);
    void deleteById(Long leafTagId);
    void delete(LeafTag leafTagId);
    Long existsByMemberIdAndName(Long memberId, String name);
}