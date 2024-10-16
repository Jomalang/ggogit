package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeafRepository extends JpaRepository<Leaf, Long> {
    List<Leaf> findByTree(Tree tree);

    @Query("from Leaf where tree.id = :treeId" +
            " AND (:owner = true bookMark = true OR :owner = false  childLeafCount = 0)")
    Page<Leaf> findByBranchQuery(Long treeId, Boolean owner,Boolean bookMark, Pageable pageable);
}
