package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.view.LeafTagView;

import java.util.List;

public interface LeafTagService {

    /**
     * LeafTag를 등록한다.
     * @param leafTag 리프 태그 Entity
     */
    LeafTag register(LeafTag leafTag);

    /**
     * LeafTag 소유자 여부를 확인한다.
     * @param memberId 회원 ID
     * @param leafTagId 리프 태그 ID
     * @return boolean 소유자 여부
     */
    boolean isOwner(Long memberId, Long leafTagId);

    /**
     * LeafTag를 수정한다.
     * @param leafTag 리프 태그 Entity
     */
    boolean modify(LeafTag leafTag);

    /**
     * LeafTag를 삭제한다.
     * @param leafTag 리프 태그 Entity
     */
    boolean remove(LeafTag leafTag);

    /**
     * LeafTag를 삭제한다.
     * @param leafTagId 리프 태그 ID
     */
    boolean remove(Long leafTagId);

    /**
     * LeafTag를 조회한다.
     * @param leafTagId 리프 태그 ID
     * @return LeafTag 리프 태그 Entity
     */
    LeafTag getLeafTag(Long leafTagId);

    /**
     * LeafTag 회원의 LeafTag 리스트를 조회한다.
     * @param memberId 회원 ID
     * @return LeafTag 리프 태그 View 리스트
     */
    List<LeafTagView> getLeafTags(Long memberId);


    List<LeafTagView> getLeafTags(Long memberId, Long page, Long size);

    /**
     * LeafTag 회원의 LeafTag 리스트를 검색 조회한다.
     * @param memberId 회원 ID
     * @param name 태그 이름
     * @return LeafTag 리프 태그 View 리스트
     */
    List<LeafTagView> getLeafTags(Long memberId, String name, Long page, Long size);
}