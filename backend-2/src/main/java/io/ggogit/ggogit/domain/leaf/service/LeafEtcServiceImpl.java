package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeafEtcServiceImpl implements LeafEtcService {

    @Override
    public Leaf createFirstLeafEtc(Long memberId, Leaf leaf, List<Long> leafTagIds) {
        return null;
    }

    @Override
    public Leaf createLeafEtc(Long memberId, Long parentLeafId, Leaf leaf, List<Long> leafTagIds) {
        return null;
    }

    @Override
    public Leaf updateLeafEtc(Long memberId, Long leafId, Leaf leaf, List<Long> leafTagIds) {
        return null;
    }

    @Override
    public void deleteLeafEtc(Long leafId) {

    }
}
