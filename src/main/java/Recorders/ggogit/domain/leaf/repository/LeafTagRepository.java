package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafTagRepository {
    Long save(LeafTag leafTag);
    List<LeafTag> findAll();
    LeafTag findById(Long id);
    int update(LeafTag leafTag);
}