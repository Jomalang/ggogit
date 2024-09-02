package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.repository.filter.LeafRepositoryFilter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafRepository {
    Long save(Leaf leaf);
    Leaf findById(Long id);
    List<Leaf> findAll(LeafRepositoryFilter filter);
    Long update(Leaf leaf);
}