package io.ggogit.ggogit.domain.tree.repository;

import io.ggogit.ggogit.domain.tree.entity.TreeSaveTmp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TreeSaveTmpRepository extends JpaRepository<TreeSaveTmp, Long> {
    Optional<TreeSaveTmp> findByMemberId(Long memberId);
}
