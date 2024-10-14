package io.ggogit.ggogit.domain.member.repository;

import io.ggogit.ggogit.domain.member.entity.MemberProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileImageRepository extends JpaRepository<MemberProfileImage, Long> {
}
