package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.type.SearchType;

import java.util.List;

public interface LeafEtcService {

    /**
     * 최초 리프 등록
     * @param leaf 리프 Entity
     */
    void register(Leaf leaf);

    /**
     * 일반 리프 등록
     * @param leaf 리프 Entity
     */
    void register(Leaf leaf, Long parentLeafId);

    /**
     * 리프 소유 확인
     * @param leaf 리프 Entity
     * @return boolean 소유 여부
     */
    boolean isOwner(Leaf leaf);

    /**
     * 리프 수정
     * @param leaf 리프 Entity
     */
    void modify(Leaf leaf);

    /**
     * 리프 삭제
     * @param leaf 리프 Entity
     */
    void remove(Leaf leaf);

    /**
     * 리프 삭제
     * @param leafId 리프 ID
     */
    void remove(Long leafId);

    /**
     * 리프 조회
     * @param leafId 리프 ID
     * @return Leaf 리프 Entity
     */
    Leaf get(Long leafId);

    /**
     * 리프 리스트 조회
     * @param treeId 트리 ID
     * @return Leaf 리프 Entity 리스트
     */
    List<Leaf> list(Long treeId);

    /**
     * 리프 리스트 조회 검색 조회
     * @param treeId 트리 ID
     * @return Leaf 리프 Entity 리스트
     */
    List<Leaf> list(Long treeId,  SearchType searchType, String search);
}