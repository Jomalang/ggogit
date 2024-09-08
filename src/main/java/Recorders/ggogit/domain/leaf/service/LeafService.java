package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.view.LeafBreadcrumbView;
import Recorders.ggogit.domain.leaf.view.LeafImageCardView;
import Recorders.ggogit.domain.leaf.view.LeafItemView;
import Recorders.ggogit.domain.leaf.view.LeafRecentBranchView;
import jakarta.annotation.Nullable;

import java.util.List;

public interface LeafService {

    /**
     * LeafItemView 리스트를 조회한다.
     * @param treeId 트리 ID
     * @param leafId 리프 ID
     * @return LeafItemView 리프 아이템 View 리스트
     */
    List<LeafItemView> getLeafItems(Long treeId, @Nullable Long leafId);


    /**
     * 리프의 브레드크럼 정보를 조회한다.
     * @param treeId 트리 ID
     * @param leafId 리프 ID
     * @return LeafBreadcrumbView 리프 브레드크럼 View
     */
    LeafBreadcrumbView getBreadcrumb(Long treeId, Long leafId);


    /**
     * 리프의 브레드크럼 정보를 조회한다.
     * @param leafId 리프 ID
     * @return LeafBreadcrumbView 리프 브레드크럼 View
     */
    LeafBreadcrumbView getBreadcrumb(Long leafId);


    /**
     * 최근 업데이트된 브랜치 정보를 조회한다.
     * @param treeId 트리 ID
     * @return LeafRecentBranchView 최근 업데이트된 브랜치 View
     */
    LeafRecentBranchView getRecentBranch(Long treeId);

    /**
     * 리프 이미지 카드 정보를 조회한다.
     * @param memberId 멤버 ID
     * @return LeafImageCardView 리프 이미지 카드 View
     */
    LeafImageCardView LeafImageCardView(Long memberId);
}
