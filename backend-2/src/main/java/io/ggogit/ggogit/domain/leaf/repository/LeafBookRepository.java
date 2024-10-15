package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeafBookRepository extends JpaRepository<LeafBook, Long> {
    List<LeafBook> findByIdIn(List<Long> ids);
    Optional<LeafBook> findByLeaf(Leaf lf);
}
