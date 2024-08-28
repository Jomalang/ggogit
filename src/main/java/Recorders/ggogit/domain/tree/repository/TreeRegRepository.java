package Recorders.ggogit.domain.tree.repository;

import Recorders.ggogit.domain.tree.entity.Tree;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TreeRegRepository {

    void addTreeRow(Tree tree);

}
