package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeafBookRepository extends JpaRepository<LeafBook, Long> {
    List<LeafBook> findByLeaf_Tree_Id(Long id);
}
