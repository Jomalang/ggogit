package io.ggogit.ggogit.domain.member.repository;

import io.ggogit.ggogit.domain.member.entity.EmailJoinToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailJoinTokenRepository extends JpaRepository<EmailJoinToken, Long> {

    void deleteByEmail(String email);

    Optional<EmailJoinToken> findByEmail(String email);

    Optional<EmailJoinToken> findByUuid(String key);
}
