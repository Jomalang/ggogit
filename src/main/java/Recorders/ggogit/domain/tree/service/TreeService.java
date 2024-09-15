package Recorders.ggogit.domain.tree.service;


import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.view.BookTreeView;
import Recorders.ggogit.domain.tree.view.EtcTreeView;
import Recorders.ggogit.domain.tree.view.TreeInfoView;
import Recorders.ggogit.web.tree.form.TreeEtcSaveTmpForm;
import Recorders.ggogit.web.tree.form.TreeSaveTmpForm;

import java.util.List;


public interface TreeService {

    /***
     * 트리 생성 메서드
     *
     * @param tree ^^
     */
    void register(Tree tree);

    /***
     * 트리 문자열 탐색 전체 정보 조회
     * 도서 테이블 + 트리 테이블 + 트리 소속 리프 마지막 수정시간
     *
     * @param memberId
     * @return
     */
    List<TreeInfoView> getTreeInfoView(String str);



    /***
     * 멤버 ID 별 트리 갯수 조회
     *
     * @param memberId
     * @return
     */
    long hasTreeNumById(Long id);

    /***
     * 멤버 ID 별 트리 전체 정보 조회
     * 도서 테이블 + 트리 테이블 + 트리 소속 리프 마지막 수정시간
     *
     * @param memberId
     * @return
     */
    List<TreeInfoView> getTreeInfoView(Long memberId);

    /***
     * 멤버 ID 별 도서 + 트리 정보 조회
     * 도서 테이블 + 트리 테이블
     *
     * @param memberId
     * @return
     */
    List<BookTreeView> getBookTreeView(Long memberId);

    /***
     * 멤버 ID 별 도서가 아닌 트리 전체 정보 조회
     * !도서 테이블 + 트리 테이블
     *
     * @param memberId
     * @return
     */
    List<EtcTreeView> getEtcTreeview(Long memberId);

    /***
     * 트리 완독 여부 조회
     *
     * @param memberId
     * @return
     */
    Boolean getComplate(Long treeId);

    /***
     * 트리 ID 별 트리 제거
     *
     *
     * @param memberId
     * @return
     */
    void delete(Long treeId);

    /***
     * 트리 생성 TMP 테이블 생성
     *
     *
     * @param TreeSaveTmpForm
     * @return
     */
    void tmpTreeSave(TreeSaveTmpForm form);

    /***
     * 트리 생성 TMP 테이블 생성
     *
     *
     * @param TreeSaveTmpForm
     * @return
     */
    void tmpEtcTreeSave(TreeEtcSaveTmpForm form);

    /***
     * 트리 생성 TMP 테이블 MEMBERID 중복 삭제
     *
     *
     * @param memberId
     * @return
     */
    void deleteTmpFormById(Long memberId);
}
