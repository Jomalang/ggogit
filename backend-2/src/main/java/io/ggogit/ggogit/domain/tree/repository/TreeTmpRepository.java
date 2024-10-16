package io.ggogit.ggogit.domain.tree.repository;

import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TreeTmpRepository extends JpaRepository<TreeTmp, Long> {
    Optional<TreeTmp> findByMemberId(Long memberId);
}
