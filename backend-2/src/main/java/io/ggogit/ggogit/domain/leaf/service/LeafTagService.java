package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import org.springframework.data.domain.Page;

public interface LeafTagService {

    LeafTag register(Long memberId, String name);

    LeafTag modify(Long memberId, Long tagId, String name);

    boolean isOwner(Long memberId, Long tagId);

    void remove(Long memberId, Long tagId);

    Page<LeafTag> list(Long memberId, String searchName, int page, int size);

    LeafTag detail(Long tagId);
    boolean isExist(Long memberId, String name);

//    /**
//     * LeafTag를 등록한다.
//     * @param leafTag 리프 태그 Entity
//     */
//    LeafTag register(LeafTag leafTag);
//
//    /**
//     * LeafTag 소유자 여부를 확인한다.
//     * @param memberId 회원 ID
//     * @param leafTagId 리프 태그 ID
//     * @return boolean 소유자 여부
//     */
//    boolean isOwner(Long memberId, Long leafTagId);
//
//    /**
//     * LeafTag를 수정한다.
//     * @param leafTag 리프 태그 Entity
//     */
//    LeafTag modify(LeafTag leafTag);
//
//    /**
//     * LeafTag를 삭제한다.
//     * @param leafTag 리프 태그 Entity
//     */
//    boolean remove(LeafTag leafTag);
//
//    /**
//     * LeafTag를 삭제한다.
//     * @param leafTagId 리프 태그 ID
//     */
//    boolean remove(Long memberId, Long leafTagId);
//
//    /**
//     * LeafTag를 조회한다.
//     * @param leafTagId 리프 태그 ID
//     * @return LeafTag 리프 태그 Entity
//     */
//    LeafTag getLeafTag(Long leafTagId);
//
//    /**
//     * LeafTag 회원의 LeafTag 리스트를 조회한다.
//     * @param memberId 회원 ID
//     * @return LeafTag 리프 태그 View 리스트
//     */
//    List<LeafTag> getLeafTags(Long memberId);
//
//
//    List<LeafTag> getLeafTags(Long memberId, Long page, Long size);
//
//    /**
//     * LeafTag 회원의 LeafTag 리스트를 검색 조회한다.
//     * @param memberId 회원 ID
//     * @param name 태그 이름
//     * @return LeafTag 리프 태그 View 리스트
//     */
//    List<LeafTag> getLeafTags(Long memberId, String name, Long page, Long size);
//
//    List<LeafTag> getLeafTagsByLeafId(Long memberId);
}