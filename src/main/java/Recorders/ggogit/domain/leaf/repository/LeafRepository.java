package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeafRepository {
    Long save(Leaf leaf);
    List<Leaf> findByUserId(Long userId);
    List<Leaf> findAll();
    Long update(Leaf leaf);
}