package Recorders.ggogit.domain.leaf;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeafRepository {

    void save(Leaf leaf);

}