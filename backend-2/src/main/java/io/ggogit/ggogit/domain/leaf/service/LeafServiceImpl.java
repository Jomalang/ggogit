package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.repository.LeafRepository;
import io.ggogit.ggogit.type.filterType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LeafServiceImpl implements LeafService {
    private final LeafRepository leafRepository;

    @Override
    public Page<Leaf> findBranchByFilter(Long treeId,Boolean owner,Boolean bookMark, Pageable pageable) {
        return leafRepository.findByBranchQuery(treeId, owner, bookMark, pageable);
    }
}
