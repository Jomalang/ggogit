package io.ggogit.ggogit.domain.leaf.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.QLeaf;
import io.ggogit.ggogit.domain.tree.entity.QTree;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LeafQueryDslRepositoryImpl implements LeafQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Leaf> findByLeafId(Long leafId) {
        QLeaf leaf = QLeaf.leaf;

        return Optional.ofNullable(queryFactory.selectFrom(leaf)
                .where(leaf.id.eq(leafId))
                .fetchOne());
    }

    @Override
    public Page<Leaf> getBookCards(Long memberId, int page, int size) {
        // 회원이 최근 생성한 공개 가능한 리프들 조회
        QLeaf leaf = QLeaf.leaf;
        QTree tree = QTree.tree;

        long count = queryFactory
                .selectFrom(leaf)
                .where(
                        leaf.tree.id.in(
                                JPAExpressions
                                        .select(tree.id)
                                        .from(tree)
                                        .where(
                                                tree.member.id.eq(memberId),
                                                tree.visibility.eq(true),
                                                leaf.isDeleted.eq(false)
                                        )
                        ),
                        leaf.visibility.eq(true),
                        leaf.isDeleted.eq(false)
                )
                .fetchCount();

        List<Leaf> leaves = queryFactory
                .selectFrom(leaf)
                .where(
                        leaf.tree.id.in(
                                JPAExpressions
                                        .select(tree.id)
                                        .from(tree)
                                        .where(
                                                tree.member.id.eq(memberId),
                                                tree.visibility.eq(true),
                                                leaf.isDeleted.eq(false)
                                        )
                        ),
                        leaf.visibility.eq(true),
                        leaf.isDeleted.eq(false)
                )
                .orderBy(leaf.updateTime.desc())
                .offset((long) (page - 1) * size)
                .limit(size)
                .fetch();

        return new PageImpl<>(leaves, PageRequest.of(page, size), count);
    }
}