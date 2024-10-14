package io.ggogit.ggogit.domain.member.repository;

import io.ggogit.ggogit.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
