package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.structure.LeafNode;
import Recorders.ggogit.domain.leaf.view.*;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;
import Recorders.ggogit.type.filterType;

import java.util.List;

public interface LeafService {
    List<LeafItemView> getLeafItems(Long treeId, Long leafId, boolean isOwner);

    LeafListBranchView getBranchInfo(Long treeId, Long leafId);

    LeafBreadcrumbView getBreadcrumb(Long treeId, Long leafId);
    LeafBreadcrumbView getBreadcrumb(Long leafId);

    List<LeafImageCardView> getLeafImageCardViews(Long memberId);
    List<LeafImageCardView> getLeafImageCardViews(Long memberId, SearchType searchType, String search, SortType sortType, Long page, Long size);


    List<LeafCardView> getLeafCardViews(Long bookId, Long memberId);
    List<LeafCardView> getLeafCardViews(Long bookId, Long memberId, SearchType searchType, String search, SortType sortType, Long page, Long size);

    List<LeafNode> getLeafNodeFromLeafIdToEnd(Long treeId, Long leafId, boolean isOwner);

    List<LeafNode> getLeafNodeAll(Long treeId, Long leafId, boolean isOwner);

    BeforeLeafInfoView getBeforeLeafInfoView(Long leafId);

    boolean isOwner(Long treeId, Long memberId);

    List<LeafBranchView> findBranchByTreeId(Long treeId);

    List<LeafBranchView> findBranch(Long treeId, Boolean isLeaf, Long filter, Long sort, int page);
}
