package io.ggogit.ggogit.domain.memoir.repository;

import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoirRepository extends JpaRepository<Memoir, Long> {
}
