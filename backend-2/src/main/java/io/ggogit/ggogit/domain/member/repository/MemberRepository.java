package io.ggogit.ggogit.domain.member.repository;

import io.ggogit.ggogit.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member findByNickname(String nickname);

    Member findByUsername(String username);

}
