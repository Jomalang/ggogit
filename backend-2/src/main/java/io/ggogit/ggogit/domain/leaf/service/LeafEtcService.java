package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.api.leaf.dto.EtcLeafEditResponse;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;

import java.util.List;

public interface LeafEtcService {
    Leaf createFirstLeafEtc(Long memberId, Leaf leaf, List<Long> leafTagIds);
    Leaf createLeafEtc(Long memberId, Long parentLeafId, Leaf leaf, List<Long> leafTagIds);
    Leaf updateLeafEtc(Long memberId, Long leafId, Leaf toLeaf, List<Long> toLeafTagIds);
    void deleteLeafEtc(Long leafId);
    boolean isOwner(Long memberId, Long parentLeafId);

    EtcLeafEditResponse getLeafEtcEdit(Long leafId);
}