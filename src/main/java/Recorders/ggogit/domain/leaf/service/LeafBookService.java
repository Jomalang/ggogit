package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.view.LeafBookView;
import Recorders.ggogit.Type.SearchType;

import java.util.List;

public interface LeafBookService {

    /**
     * 최초 도서 리프 등록
     * @param leafBookView 리프 도서 View
     */
    void register(LeafBookView leafBookView);

    /**
     * 일반 도서 리프 등록
     * @param leafBookView 리프 도서 View
     */
    void register(LeafBookView leafBookView, Long parentLeafId);

    /**
     * 도서 리프 소유 확인
     * @param leafBookView 리프 도서 View
     * @return boolean 소유 여부
     */
    boolean isOwner(LeafBookView leafBookView);

    /**
     * 도서 리프 수정
     * @param leafBookView 리프 도서 View
     */
    void modify(LeafBookView leafBookView);

    /**
     * 도서 리프 삭제
     * @param leafBookView 리프 도서 View
     */
    void remove(LeafBookView leafBookView);

    /**
     * 도서 리프 조회
     * @param leafBookId 리프 도서 ID
     */
    LeafBookView get(Long leafBookId);

    /**
     * 도서 리프 리스트 조회
     * @param treeId 트리 ID
     * @return LeafBookView 리프 도서 View 리스트
     */
    List<LeafBookView> list(Long treeId);

    /**
     * 도서 리프 리스트 조회 검색 조회
     * @param treeId 트리 ID
     * @return LeafBookView 리프 도서 View 리스트
     */
    List<LeafBookView> list(Long treeId, SearchType searchType, String search);
}