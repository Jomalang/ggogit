package io.ggogit.ggogit.domain.tree.repository.query;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.domain.tree.entity.QTree;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

import static io.ggogit.ggogit.domain.book.entity.QBook.book;
import static io.ggogit.ggogit.domain.book.entity.QBookCategory.bookCategory;
import static io.ggogit.ggogit.domain.leaf.entity.QLeaf.leaf;
import static io.ggogit.ggogit.domain.tree.entity.QTree.tree;
import static io.ggogit.ggogit.domain.tree.entity.QTreeBook.treeBook;

@RequiredArgsConstructor
@Repository
public class TreeQueryRepositoryImpl implements TreeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Tree> findTreeByMemberIdFetch(Long memberId) {
        return queryFactory
                .selectFrom(tree)
                .join(tree.leaf, leaf).fetchJoin()
                .join(tree.book, book).fetchJoin()
                .join(tree.book.bookCategory, bookCategory).fetchJoin()
                .join(tree.treeBook, treeBook).fetchJoin()
                .where(tree.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public List<Tree> findTreeByMemberIdFetch(Long memberId, Long seedId) {
        return queryFactory
                .selectFrom(tree)
                .join(tree.leaf, leaf).fetchJoin()
                .join(tree.book, book).fetchJoin()
                .join(tree.book.bookCategory, bookCategory).fetchJoin()
                .join(tree.treeBook, treeBook).fetchJoin()
                .where(tree.member.id.eq(memberId).and(tree.seed.id.eq(seedId)))
                .fetch();

    }

    @Override
    public Page<Tree> findByQueryAndMemberId(String query, String filter, Long memberId, Pageable pageable) {
        QTree tree = QTree.tree; // QTree는 QueryDSL로 생성된 Q타입 클래스입니다.

        if (query == null) {
            query = "";
        }

        BooleanExpression condition = tree.member.id.eq(memberId);

        switch (filter) {
            case "title":
                condition = condition.and(tree.title.like("%" + query + "%")
                        .or(tree.book.title.like("%" + query + "%")));
                break;
            case "author":
                condition = condition.and(tree.book.author.like("%" + query + "%"));
                break;
            case "publisher":
                condition = condition.and(tree.book.publisher.like("%" + query + "%"));
                break;
            default:
                throw new IllegalArgumentException("Invalid filter: " + filter);
        }

        // Sort 정보를 가져와서 동적으로 orderBy 조건을 추가
        JPAQuery<Tree> jpaQuery = queryFactory
                .selectFrom(tree)
                .where(condition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        // Sort 적용
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder<Object> pathBuilder = new PathBuilder<>(tree.getType(), tree.getMetadata());
            jpaQuery.orderBy(new OrderSpecifier(
                    order.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(order.getProperty())
            ));
        }

        List<Tree> trees = jpaQuery.fetch();

        Long total = queryFactory
                .select(tree.count())
                .from(tree)
                .where(condition)
                .fetchOne();

        return PageableExecutionUtils.getPage(trees, pageable, () -> total != null ? total : 0);
    }
}
