package io.ggogit.ggogit.domain.member.repository.query;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.api.member.dto.MemberDomainCntResponse;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ggogit.ggogit.domain.book.entity.QBook.book;
import static io.ggogit.ggogit.domain.member.entity.QMember.member;
import static io.ggogit.ggogit.domain.tree.entity.QTree.tree;

@Repository
public class MemberQueryRepositoryImpl implements MemberQueryRepository{


    private final JPAQueryFactory jpaQueryFactory;

    public MemberQueryRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Long findBookCntById(Long memberId) {

        return (long)jpaQueryFactory
                .selectFrom(book)
                .where(book.member.id.eq(memberId))
                .fetch().size();
    }
}
