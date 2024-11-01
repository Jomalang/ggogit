package io.ggogit.ggogit.domain.tree.repository.query;

import io.ggogit.ggogit.domain.tree.entity.Tree;

import java.util.List;

public interface TreeQueryRepository {

    public List<Tree> findTreeByMemberIdFetch(Long memberId);
}
