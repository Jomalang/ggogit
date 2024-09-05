package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LeafTagRepository {
    Long save(LeafTag leafTag);
    Optional<LeafTag> findById(Long id);
    List<LeafTag> findAll(Long memberId, String name);
    Long update(LeafTag leafTag);
}