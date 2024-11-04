package io.ggogit.ggogit.domain.leaf.service;


import io.ggogit.ggogit.api.leaf.dto.*;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
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
    LeafItemToEndResponse getLeafNodeToEnd(Long leafId, boolean isOwner);

    Page<Leaf> findLeafByTreeId(Long leafId, Boolean isOwner, Pageable pageable);

    Leaf queryCheck(Long leafId);

    HashMap<String ,Integer> nodeCountToRoot(Leaf leaf);

    List<LeafBranchResponse> findBranchByFilter(Long treeId, Boolean owner, Boolean bookMark);

    LeafBookDetailResponse getBookDetail(Long leafId);

    LeafBreadcrumbResponse getLeafBreadcrumb(Long leafId);

    LeafBookEditDetailResponse getLeafBookEditDetail(Long leafId);

    LeafBeforeNodeInfoResponse getLeafBeforeNodeInfo(Long leafId);

    LeafEtcEditDetailResponse getEtcLeafEditDetail(Long leafId);

    LeafEtcDetailResponse getLeafEtcDetail(Long leafId);

    LeafBookCardResponse getLeafBookCards(Long memberId, int page, int size);

    LeafCardResponse getLeafCards(Long bookId, int page, int size);

    String getSeedType(Long leafId);
}