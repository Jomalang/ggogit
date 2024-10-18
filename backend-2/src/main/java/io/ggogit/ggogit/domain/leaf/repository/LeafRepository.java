package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeafRepository extends JpaRepository<Leaf, Long> {
    List<Leaf> findByTree(Tree tree);

    @Query("""
    SELECT l FROM Leaf l
    WHERE l.tree.id = :treeId
    AND (:owner = true AND l.bookMark = :bookMark AND l.childLeafCount = 0)
    OR (:owner = false AND l.childLeafCount = 0)
""")
    List<Leaf> findByBranchQuery(
            @Param("treeId") Long treeId,
            @Param("owner") Boolean owner,
            @Param("bookMark") Boolean bookMark);
}
