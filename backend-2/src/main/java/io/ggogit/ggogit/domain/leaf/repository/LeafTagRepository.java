package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LeafTagRepository extends JpaRepository<LeafTag, Long> {

    Optional<LeafTag> findByMemberAndNameContaining(Member member, String name);

    Optional<LeafTag> findByMemberAndName(Member member, String name);

    @Query("""
        FROM LeafTag
        WHERE member = :member
            AND (:name IS NULL OR name LIKE concat('%', :name, '%'))
    """)
    Page<LeafTag> findByMemberAndName(Member member, String name, Pageable pageable);
}
