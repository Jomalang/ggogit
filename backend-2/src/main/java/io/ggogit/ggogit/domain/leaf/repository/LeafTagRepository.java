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

    @Query("""
        select t
        from LeafTag t
        where t.member = :member
            and (t.name is null or t.name like %:name%)
    """)
    Page<LeafTag> findByMemberAndName(Member member, String name, Pageable pageable);
}
