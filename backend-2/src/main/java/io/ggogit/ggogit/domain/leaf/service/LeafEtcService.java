package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;

import java.util.List;

public interface LeafEtcService {
    Leaf createFirstLeafEtc(Long memberId, Leaf leaf, List<Long> leafTagIds);
    Leaf createLeafEtc(Long memberId, Long parentLeafId, Leaf leaf, List<Long> leafTagIds);
    Leaf updateLeafEtc(Long memberId, Long leafId, Leaf leaf, List<Long> leafTagIds);
    void deleteLeafEtc(Long leafId);
//    LeafEtcView register(LeafEtcView leafEtcView, Long memberId);
//    LeafEtcView registerRoot(LeafEtcView leafEtcView, Long memberId);
//    LeafEtcView registerNode(LeafEtcView leafEtcView, Long parentLeafId, Long memberId);
//    boolean modify(LeafEtcView leafEtcView);
//    void remove(Long leafId);
//    LeafEtcView getLeafEtcView(Long leafId);
//    List<LeafEtcView> getLeafEtcViews(Long treeId);
//    List<LeafEtcView> getLeafEtcViews(Long treeId, SearchType searchType, String search);
//    List<LeafEtcView> getLeafEtcViews(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size);
}