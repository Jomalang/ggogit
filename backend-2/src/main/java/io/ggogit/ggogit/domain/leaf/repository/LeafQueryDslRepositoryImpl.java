package io.ggogit.ggogit.domain.leaf.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.QLeaf;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}