package io.ggogit.ggogit.domain.tree.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
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
import static io.ggogit.ggogit.domain.memoir.entity.QMemoir.memoir;
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
        List<Tree> result = queryFactory
                .selectFrom(tree)
                .join(tree.member, member).fetchJoin()
                .join(tree.book, book).fetchJoin()
                .join(tree.treeBook, treeBook).fetchJoin()
                .join(tree.memoir, memoir).fetchJoin()
//                .join(tree.leaf, leaf).fetchJoin()
//                .join(tree.book.bookCategory, bookCategory).fetchJoin()
                .where(memberEq(memberId))
                .fetch();

        System.out.println("String.valueOf(result.size()) = " + result.size());
        return result;
    }

    @Override
    public List<Tree> findTreeByMemberIdFetch(Long memberId, Long seedId) {
        return queryFactory
                .selectFrom(tree)
//                .join(tree.leaf, leaf).fetchJoin()
//                .join(tree.book.bookCategory, bookCategory).fetchJoin()
//                .join(tree.seed, seed).fetchJoin()
                .join(tree.book, book).fetchJoin()
                .join(tree.treeBook, treeBook).fetchJoin()
                .join(tree.member, member).fetchJoin()
                .where(memberEq(memberId), seedEq(seedId))
                .fetch();

    }

    @Override
    public Page<Tree> findTreeByMemberIdFetch(Long memberId, Pageable pageable) {
        List<Tree> result = queryFactory
                .selectFrom(tree)
//                .join(tree.leaf, leaf).fetchJoin()
//                .join(tree.book.bookCategory, bookCategory).fetchJoin()
                .join(tree.book, book).fetchJoin()
                .join(tree.treeBook, treeBook).fetchJoin()
                .join(tree.member, member).fetchJoin()
                .where(memberEq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<Tree> findAllByMemberIdAndBookId(Long memberId, Long bookId, Pageable pageable) {

        List<Tree> result = queryFactory
                .selectFrom(tree)
//                .join(tree.leaf, leaf).fetchJoin()
//                .join(tree.book.bookCategory, bookCategory).fetchJoin()
                .join(tree.treeBook, treeBook).fetchJoin()
                .join(tree.member, member).on(tree.member.id.eq(member.id))
                .join(tree.book, book).on(tree.book.id.eq(book.id))
                .where(memberEq(memberId), bookEq(bookId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        for(Tree t : result){
            System.out.println("tree = " + t.toString());
        }


        return new PageImpl<>(result, pageable, result.size());
    }


}
