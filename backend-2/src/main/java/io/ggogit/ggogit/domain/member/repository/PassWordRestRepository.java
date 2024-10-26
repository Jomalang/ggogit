package io.ggogit.ggogit.domain.member.repository;

import io.ggogit.ggogit.domain.member.entity.PassWordRest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassWordRestRepository extends JpaRepository<PassWordRest, Long> {
    void deleteByEmail(String email);

    Optional<PassWordRest> findByUuid(String token);
}
