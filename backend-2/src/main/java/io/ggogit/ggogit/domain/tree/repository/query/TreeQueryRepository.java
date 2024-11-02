package io.ggogit.ggogit.domain.tree.repository.query;

import io.ggogit.ggogit.domain.tree.entity.Tree;

import java.util.List;

public interface TreeQueryRepository {

    /**
     * memberId로 Tree와 Leaf를 fetchJoin하여 조회
     * 결과집합이 지나치게 커지는 것을 방지하기 위해 회고록은 지연로딩으로 조회
     * @param memberId
     * @return TreeList
     */
    public List<Tree> findTreeByMemberIdFetch(Long memberId);
}
