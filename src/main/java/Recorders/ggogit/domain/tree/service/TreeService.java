package Recorders.ggogit.domain.tree.service;


import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.view.BookTreeView;
import Recorders.ggogit.domain.tree.view.EtcTreeView;
import Recorders.ggogit.domain.tree.view.TreeInfoView;

import java.util.List;

public interface TreeService {

    /***
     * 트리 생성 메서드
     *
     * @param tree
     */
    void register(Tree tree);

    /***
     * 멤버 ID 별 트리 전체 정보 조회
     * 도서 테이블 + 트리 테이블 + 트리 소속 리프 마지막 수정시간
     *
     * @param memberId
     * @return
     */
    List<TreeInfoView> getTreeInfoView(Long memberId);

    List<BookTreeView> getBookTreeView(Long memberId);

    List<EtcTreeView> getEtcTreeview(Long memberId);

    List<>

}
