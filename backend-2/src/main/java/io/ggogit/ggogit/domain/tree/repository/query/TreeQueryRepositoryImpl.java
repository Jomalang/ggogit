package io.ggogit.ggogit.domain.tree.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;

import static io.ggogit.ggogit.domain.leaf.entity.QLeaf.leaf;
import static io.ggogit.ggogit.domain.tree.entity.QTree.tree;

@RequiredArgsConstructor
@Repository
public class TreeQueryRepositoryImpl implements TreeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Tree> findTreeByMemberIdFetch(Long memberId) {
        return queryFactory
                .selectFrom(tree)
                .join(tree.leaf, leaf).fetchJoin()
                .where(tree.member.id.eq(memberId))
                .fetch();
    }
}
