package io.ggogit.ggogit.domain.memoir.repository.query;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemoirQueryRepositoryImpl {

    private final JPAQueryFactory queryFactory;
}
