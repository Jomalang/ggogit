package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeafRepository extends JpaRepository<Leaf, Long> {
    List<Leaf> findByTree(Tree tree);
    List<Leaf> findByTreeOrderById(Tree tree);
}
