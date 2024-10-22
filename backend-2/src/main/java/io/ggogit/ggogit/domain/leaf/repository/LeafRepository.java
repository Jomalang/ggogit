package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeafRepository extends JpaRepository<Leaf, Long> {
    List<Leaf> findByTree(Tree tree);
    List<Leaf> findByTreeOrderById(Tree tree);

    @Query("""
    SELECT l FROM Leaf l
    WHERE l.tree.id = :treeId
    AND l.isDeleted = false
    AND (
        (:owner = true AND (
            (:bookMark IS NULL AND (l.bookMark = true OR l.childLeafCount = 0))
                OR(:bookMark = true AND l.bookMark = true)
                OR(:bookMark = false AND l.childLeafCount = 0)
        ))
        OR (:owner = false AND l.visibility = false AND l.childLeafCount = 0)
    )
""")
    List<Leaf> findByBranchQuery(
            @Param("treeId") Long treeId,
            @Param("owner") Boolean owner,
            @Param("bookMark") Boolean bookMark);
}
