package io.ggogit.ggogit.domain.member.repository;

import io.ggogit.ggogit.api.member.dto.MemberImageDto;
import io.ggogit.ggogit.domain.member.entity.MemberProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberProfileImageRepository extends JpaRepository<MemberProfileImage, Long> {

    @Query("SELECT new io.ggogit.ggogit.api.member.dto.MemberImageDto(m.id, m.nickname, m.username, mp.name) " +
            "FROM Member m LEFT JOIN MemberProfileImage mp ON m.id = mp.member.id " +
            "WHERE m.id = :memberId")
    MemberImageDto getMemberImageDto(@Param("memberId") Long memberId);
}
