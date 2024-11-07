package io.ggogit.ggogit.domain.tree.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.List;

import static io.ggogit.ggogit.domain.book.entity.QBook.book;
import static io.ggogit.ggogit.domain.book.entity.QBookCategory.bookCategory;
import static io.ggogit.ggogit.domain.leaf.entity.QLeaf.leaf;
import static io.ggogit.ggogit.domain.member.entity.QMember.member;
import static io.ggogit.ggogit.domain.tree.entity.QSeed.seed;
import static io.ggogit.ggogit.domain.tree.entity.QTree.tree;
import static io.ggogit.ggogit.domain.tree.entity.QTreeBook.treeBook;

@RequiredArgsConstructor
@Repository
public class TreeQueryRepositoryImpl implements TreeQueryRepository {

    private final JPAQueryFactory queryFactory;

    private BooleanExpression memberEq(Long memberId) {
        return memberId != null ? tree.member.id.eq(memberId) : null;
    }

    private BooleanExpression seedEq(Long seedId) {
        return seedId != null ? tree.seed.id.eq(seedId) : null;
    }

    private BooleanExpression bookEq(Long bookId) {
        return bookId != null ? tree.book.id.eq(bookId) : null;
    }

    public List<Tree> findTreeByMemberIdFetch(Long memberId) {
        return queryFactory
                .selectFrom(tree)
                .join(tree.leaf, leaf).fetchJoin()
                .join(tree.book, book).fetchJoin()
                .join(tree.book.bookCategory, bookCategory).fetchJoin()
                .join(tree.treeBook, treeBook).fetchJoin()
                .join(tree.member, member).fetchJoin()
                .where(memberEq(memberId))
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
                .join(tree.seed, seed).fetchJoin()
                .join(tree.member, member).fetchJoin()
                .where(memberEq(memberId), seedEq(seedId))
                .fetch();

    }

    @Override
    public Page<Tree> findTreeByMemberIdFetch(Long memberId, Pageable pageable) {
        List<Tree> result = queryFactory
                .selectFrom(tree)
                .join(tree.leaf, leaf).fetchJoin()
                .join(tree.book, book).fetchJoin()
                .join(tree.book.bookCategory, bookCategory).fetchJoin()
                .join(tree.treeBook, treeBook).fetchJoin()
                .join(tree.member, member).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(memberEq(memberId))
                .fetch();

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<Tree> findAllByMemberIdAndBookId(Long memberId, Long bookId, Pageable pageable) {

        List<Tree> result = queryFactory
                .selectFrom(tree)
                .join(tree.leaf, leaf).fetchJoin()
                .join(tree.book, book).fetchJoin()
                .join(tree.book.bookCategory, bookCategory).fetchJoin()
                .join(tree.treeBook, treeBook).fetchJoin()
                .join(tree.member, member).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(bookEq(bookId), memberEq(memberId))
                .fetch();

        return new PageImpl<>(result, pageable, result.size());
    }


}
