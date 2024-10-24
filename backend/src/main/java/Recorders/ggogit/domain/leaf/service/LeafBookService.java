package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.view.LeafBookView;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;

import java.util.List;

public interface LeafBookService {
    LeafBookView register(LeafBookView leafBookView, Long seedId, Long memberId);
    LeafBookView registerRoot(LeafBookView leafBookView, Long seedId, Long memberId);
    LeafBookView registerNode(LeafBookView leafBookView, Long seedId, Long memberId);
    boolean modify(LeafBookView leafBookView);
    boolean remove(Long leafId);
    LeafBookView getLeafBookView(Long leafBookId);
    List<LeafBookView> getLeafBookViews(Long treeId);
    List<LeafBookView> getLeafBookViews(Long treeId, SearchType searchType, String search);
    List<LeafBookView> getLeafBookViews(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size);
}