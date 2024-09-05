package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.view.LeafTagView;

import java.util.List;

public interface LeafTagService {

    /**
     * LeafTag를 등록한다.
     * @param leafTag 리프 태그 Entity
     */
    void register(LeafTag leafTag);

    /**
     * LeafTag 소유 확인
     * @param leafTag 리프 태그 Entity
     * @return boolean 소유 여부
     */
    boolean isOwner(LeafTag leafTag);

    /**
     * LeafTag를 수정한다.
     * @param leafTag 리프 태그 Entity
     */
    void modify(LeafTag leafTag);

    /**
     * LeafTag를 삭제한다.
     * @param leafTag 리프 태그 Entity
     */
    void remove(LeafTag leafTag);

    /**
     * LeafTag를 삭제한다.
     * @param leafTagId 리프 태그 ID
     */
    void remove(Long leafTagId);

    /**
     * LeafTag를 조회한다.
     * @param leafTagId 리프 태그 ID
     * @return LeafTagView 리프 태그 View
     */
    LeafTagView getLeafTagView(Long leafTagId);

    /**
     * LeafTag 회원의 LeafTag 리스트를 조회한다.
     * @param memberId 회원 ID
     * @return LeafTagView 리프 태그 View 리스트
     */
    List<LeafTagView> getLeafTagViews(Long memberId);

    /**
     * LeafTag 회원의 LeafTag 리스트를 검색 조회한다.
     * @param memberId 회원 ID
     * @param name 태그 이름
     * @return LeafTagView 리프 태그 View 리스트
     */
    List<LeafTagView> getLeafTagViews(Long memberId, String name);
}