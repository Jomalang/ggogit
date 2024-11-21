package io.ggogit.ggogit.domain.leaf.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
//import io.ggogit.ggogit.domain.leaf.entity.QLeaf;
//import io.ggogit.ggogit.domain.tree.entity.QTree;
import io.ggogit.ggogit.domain.leaf.entity.QLeaf;
import io.ggogit.ggogit.domain.tree.entity.QTree;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static io.ggogit.ggogit.domain.book.entity.QBook.book;
import static io.ggogit.ggogit.domain.leaf.entity.QLeaf.*;
import static io.ggogit.ggogit.domain.member.entity.QMember.member;
import static io.ggogit.ggogit.domain.tree.entity.QTree.tree;

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

    @Override
    public Page<Leaf> getLeafCards(Long bookId, int page, int size) {

        QLeaf leaf = QLeaf.leaf;

        List<Leaf> leaves = queryFactory
                .selectFrom(leaf)
                .join(leaf.tree, tree).fetchJoin()
                .join(tree.member, member).fetchJoin()
                .join(tree.book, book).fetchJoin()
                .where(
                        leaf.tree.book.id.eq(bookId),
                        leaf.visibility.eq(true)
                )
                .orderBy(leaf.updateTime.desc())
                .offset((long) (page - 1) * size)
                .limit(size)
                .fetch();

        return new PageImpl<>(leaves, PageRequest.of(page, size), leaves.size());
    }

    @Override
    public Page<Leaf> findByQueryAndMemberId(String query, String searchFilter, Long memberId, Pageable pageable) {
        QLeaf leaf = QLeaf.leaf;

        if (query == null) {
            query = "";
        }

        BooleanExpression condition = leaf.tree.member.id.eq(memberId);

        switch (searchFilter) {
            case "title":
                condition = condition.and(leaf.title.lower().like("%" + query.toLowerCase() + "%"));
                break;
            case "content":
                condition = condition.and(leaf.content.lower().like("%" + query.toLowerCase() + "%"));
                break;
            case "all":
                condition = condition.and(leaf.title.lower().like("%" + query.toLowerCase() + "%")
                        .or(leaf.content.lower().like("%" + query.toLowerCase() + "%")));
                break;
            default:
                throw new IllegalArgumentException("Invalid filter: " + searchFilter);
        }

        // Sort 정보를 가져와서 동적으로 orderBy 조건을 추가
        JPAQuery<Leaf> jpaQuery = queryFactory
                .selectFrom(leaf)
                .where(condition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        // Sort 적용
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder<Object> pathBuilder = new PathBuilder<>(leaf.getType(), leaf.getMetadata());
            jpaQuery.orderBy(new OrderSpecifier(
                    order.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(order.getProperty())
            ));
        }

        List<Leaf> leaves = jpaQuery.fetch();

        Long total = queryFactory
                .select(leaf.count())
                .from(leaf)
                .where(condition)
                .fetchOne();

        return PageableExecutionUtils.getPage(leaves, pageable, () -> total != null ? total : 0);
    }
}