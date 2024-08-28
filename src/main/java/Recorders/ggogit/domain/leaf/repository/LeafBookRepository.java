package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeafBookRepository {
    void save(LeafBook leafBook);
}