package io.ggogit.ggogit.domain.memoir.repository.query;


import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static io.ggogit.ggogit.domain.memoir.entity.QMemoir.memoir;

@Repository
@RequiredArgsConstructor
public class MemoirQueryRepositoryImpl {

    private final JPAQueryFactory queryFactory;
}
