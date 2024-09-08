package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.view.*;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;
import jakarta.annotation.Nullable;

import java.util.List;

public interface LeafService {
    List<LeafItemView> getLeafItems(Long treeId, @Nullable Long leafId);

    LeafRecentBranchView getRecentBranch(Long treeId);

    LeafBreadcrumbView getBreadcrumb(Long treeId, Long leafId);
    LeafBreadcrumbView getBreadcrumb(Long leafId);

    List<LeafImageCardView> getLeafImageCardViews(Long memberId);
    List<LeafImageCardView> getLeafImageCardViews(Long memberId, SearchType searchType, String search, SortType sortType, Long page, Long size);


    List<LeafCardView> getLeafCardViews(Long bookId, Long memberId);
    List<LeafCardView> getLeafCardViews(Long bookId, Long memberId, SearchType searchType, String search, SortType sortType, Long page, Long size);
}
