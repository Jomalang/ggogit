package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.view.LeafEtcView;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;

import java.util.List;

public interface LeafEtcService {
    boolean register(LeafEtcView leafEtcView, Long memberId);
    boolean registerRoot(LeafEtcView leafEtcView, Long memberId);
    boolean registerNode(LeafEtcView leafEtcView, Long parentLeafId, Long memberId);
    boolean modify(LeafEtcView leafEtcView);
    void remove(Long leafId);
    LeafEtcView getLeafEtcView(Long leafId);
    List<LeafEtcView> getLeafEtcViews(Long treeId);
    List<LeafEtcView> getLeafEtcViews(Long treeId, SearchType searchType, String search);
    List<LeafEtcView> getLeafEtcViews(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size);
}