package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.repository.filter.LeafRepositoryFilter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafRepository {

    // Create
    Long save(Leaf leaf);

    // Read
    Leaf findById(Long id);
    List<Leaf> findAll(LeafRepositoryFilter filter);
    boolean existsById(Long parentLeafId);

    // Update
    Long update(Leaf leaf);

    // Delete
    void delete(Leaf leaf);
}