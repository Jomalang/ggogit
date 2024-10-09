package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.view.LeafEtcView;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;

import java.util.List;

public interface LeafEtcService {
    LeafEtcView register(LeafEtcView leafEtcView, Long memberId);
    LeafEtcView registerRoot(LeafEtcView leafEtcView, Long memberId);
    LeafEtcView registerNode(LeafEtcView leafEtcView, Long parentLeafId, Long memberId);
    boolean modify(LeafEtcView leafEtcView);
    void remove(Long leafId);
    LeafEtcView getLeafEtcView(Long leafId);
    List<LeafEtcView> getLeafEtcViews(Long treeId);
    List<LeafEtcView> getLeafEtcViews(Long treeId, SearchType searchType, String search);
    List<LeafEtcView> getLeafEtcViews(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size);

    boolean isOwner(Long leafId, Long memberId);

    LeafEtcView update(Long leafId, LeafEtcView leafEtcView, Long memberId);
}