package io.ggogit.ggogit.domain.member.repository;

import io.ggogit.ggogit.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmail(String email);

//    void save(Member member);
//
//    void update(Member member);
//
//    void delete(Member member);
//
//    List<Member> findAll();
//
//    Member findById(Long id);
//
//    Member findByEmail(String Email);
//
//    Member findByNickname(String nickname);
//
//    Member findByUsername(String username);
//
//    MemberImageView getMemberImageView(Long memberId);
}

