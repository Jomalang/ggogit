package io.ggogit.ggogit.domain.member.repository;

import io.ggogit.ggogit.domain.member.entity.MemberBackgroundImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBackgroundImageRepository extends JpaRepository<MemberBackgroundImage, Long> {
}
