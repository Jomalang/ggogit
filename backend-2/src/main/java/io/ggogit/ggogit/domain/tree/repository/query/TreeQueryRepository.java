package io.ggogit.ggogit.domain.tree.repository.query;

import io.ggogit.ggogit.domain.tree.entity.Tree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TreeQueryRepository {

    /**
     * memberId로 Tree와 Leaf를 fetchJoin하여 조회
     * 결과집합이 지나치게 커지는 것을 방지하기 위해 회고록은 지연로딩으로 조회
     * @param memberId
     * @return TreeList
     */
    public List<Tree> findTreeByMemberIdFetch(Long memberId);
    public List<Tree> findTreeByMemberIdFetch(Long memberId, Long seedId);
    public Page<Tree> findTreeByMemberIdFetch(Long memberId, Pageable pageable);

    public  Page<Tree> findByQueryAndMemberId(String query, String filter, Long memberId, Pageable pageable);
}
