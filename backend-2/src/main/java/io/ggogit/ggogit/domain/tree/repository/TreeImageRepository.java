package io.ggogit.ggogit.domain.tree.repository;

import io.ggogit.ggogit.domain.tree.entity.TreeImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeImageRepository extends JpaRepository<TreeImage, Long> {
}
