package io.ggogit.ggogit.domain.tree.repository.query;

import io.ggogit.ggogit.domain.tree.entity.Tree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TreeQueryRepository {

    /**
     * memberId로 Tree와 Leaf를 fetchJoin하여 조회
     * Collection은 페이징을 위해 배치사이징을 이용해 조회
     * @param memberId
     * @return TreeList
     */
    public List<Tree> findTreeByMemberIdFetch(Long memberId);
    public List<Tree> findTreeByMemberIdFetch(Long memberId, Long seedId);
    public Page<Tree> findTreeByMemberIdFetch(Long memberId, Pageable pageable);
    public Page<Tree> findAllByMemberIdAndBookId(Long memberId, Long bookId, Pageable pageable);
}
