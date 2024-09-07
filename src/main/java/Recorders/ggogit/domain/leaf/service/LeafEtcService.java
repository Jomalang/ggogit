package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.view.LeafEtcView;
import Recorders.ggogit.type.SearchType;

import java.util.List;

public interface LeafEtcService {

    boolean register(LeafEtcView leafEtcView);
    boolean registerRoot(LeafEtcView leafEtcView);
    boolean registerNode(LeafEtcView leafEtcView, Long parentLeafId);

    void modify(LeafEtcView leafEtcView);
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