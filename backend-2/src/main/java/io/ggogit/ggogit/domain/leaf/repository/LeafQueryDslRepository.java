package io.ggogit.ggogit.domain.leaf.repository;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface LeafQueryDslRepository {

    Optional<Leaf> findByLeafId(Long leafId);

    Page<Leaf> getBookCards(Long memberId, int page, int size);

    Page<Leaf> getLeafCards(Long bookId, int page, int size);
}