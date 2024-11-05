package io.ggogit.ggogit.domain.tree.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.domain.tree.entity.QTree;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;


import java.util.List;

import static io.ggogit.ggogit.domain.book.entity.QBook.book;
import static io.ggogit.ggogit.domain.book.entity.QBookCategory.bookCategory;
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
                .join(tree.book, book).fetchJoin()
                .join(tree.book.bookCategory, bookCategory).fetchJoin()
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
                .where(tree.member.id.eq(memberId).and(tree.seed.id.eq(seedId)))
                .fetch();

    }

    @Override
    public Page<Tree> findByQueryAndMemberId(String query, Long memberId, Pageable pageable) {
        QTree tree = QTree.tree; // QTree는 QueryDSL로 생성된 Q타입 클래스입니다.

        if(query == null) {
            query = "";
        }

        List<Tree> trees = queryFactory
                .selectFrom(tree)
                .where(tree.member.id.eq(memberId)
                        .and(tree.title.like("%" + query + "%")
                                .or(tree.book.title.like("%" + query + "%"))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(tree.count())
                .from(tree)
                .where(tree.member.id.eq(memberId)
                        .and(tree.title.like("%" + query + "%")
                                .or(tree.book.title.like("%" + query + "%"))))
                .fetchOne();

        return PageableExecutionUtils.getPage(trees, pageable,
                () -> total != null ? total : 0);
    }
}
