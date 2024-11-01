package io.ggogit.ggogit.domain.tree.repository;

import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.query.TreeQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TreeRepository extends JpaRepository<Tree, Long>, TreeQueryRepository {
    List<Tree> findByMemberId(Long memberId);
    @Query("""
    SELECT t FROM Tree t 
    WHERE t.member.id = :memberId 
    AND t.isDeleted = false 
    AND (:seedId IS NULL OR t.seed.id = :seedId)
    """)
    Page<Tree> findByMemberIdAndSeedId(Long memberId, Long seedId, Pageable pageable);

    Page<Tree> findByMemberId(Long memberId, Pageable pageable);

    List<Tree> findByMember(Member member);
}
