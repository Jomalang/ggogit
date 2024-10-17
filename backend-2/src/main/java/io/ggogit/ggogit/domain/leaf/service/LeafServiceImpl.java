package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.api.leaf.dto.LeafItemResponse;
import io.ggogit.ggogit.domain.leaf.repository.LeafRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeafServiceImpl implements LeafService {

    private final LeafRepository leafRepository;

    @Override
    public LeafItemResponse getLeafsByTreeId(Long treeId) {
        return null;
    }
}
