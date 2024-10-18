package io.ggogit.ggogit.domain.member.repository;

import io.ggogit.ggogit.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndPassword(String email, String password);

    Member findByEmail(@Size(max = 255) @NotNull String email);

    Member findByNickname(String nickname);
}

