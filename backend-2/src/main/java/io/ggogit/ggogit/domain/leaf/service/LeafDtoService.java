package io.ggogit.ggogit.domain.leaf.service;


import io.ggogit.ggogit.api.leaf.dto.LeafBranchInfoResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafItemResponse;

import java.util.List;

public interface LeafDtoService {

    /**
     * 리프 노드의 Root 부터 End 까지 조회
     */
    LeafItemResponse getLeafNodeRootToEnd(Long leafId, boolean isOwner);


    /**
     * 리프 노드의 Root 부터 End 까지 의 브랜치 정보 조회
     */
    LeafBranchInfoResponse getBranchInfo(Long leafId);

    /**
     * 리프 노드의 End 까지 조회
     */
    LeafItemResponse getLeafNodeToEnd(Long leafId, boolean isOwner);

//    List<LeafItemView> getLeafItems(Long treeId, Long leafId, boolean isOwner);
//
//    LeafListBranchView getBranchInfo(Long treeId, Long leafId);
//
//    LeafBreadcrumbView getBreadcrumb(Long treeId, Long leafId);
//    LeafBreadcrumbView getBreadcrumb(Long leafId);
//
//    List<LeafImageCardView> getLeafImageCardViews(Long memberId);
//    List<LeafImageCardView> getLeafImageCardViews(Long memberId, SearchType searchType, String search, SortType sortType, Long page, Long size);
//
//
//    List<LeafCardView> getLeafCardViews(Long bookId, Long memberId);
//    List<LeafCardView> getLeafCardViews(Long bookId, Long memberId, SearchType searchType, String search, SortType sortType, Long page, Long size);
//
//    List<LeafNode> getLeafNodeFromLeafIdToEnd(Long treeId, Long leafId, boolean isOwner);
//
//    List<LeafNode> getLeafNodeAll(Long treeId, Long leafId, boolean isOwner);
//
//    BeforeLeafInfoView getBeforeLeafInfoView(Long leafId);
//
//    boolean isOwner(Long treeId, Long memberId);
//
//    List<LeafBranchView> findBranchByTreeId(Long treeId);
//
//    List<LeafBranchView> toBranch(Long treeId, Boolean isLeaf, Long filter, Long sort, int page);
//
//    List<LeafBranchView> toBranchForNeighbor(Long treeId, Long filter, Long sort, int page);
}
