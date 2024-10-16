package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;

public interface TreeService  {

    void register(Tree tree);

    //TreeInfoDto 생성
//    List<TreeInfoView> getTreeInfoView(Long memberId);

//    List<TreeInfoView> getTreeInfoView(Long seedId, Long memberId);

//    List<BookTreeView> getBookTreeView(Long memberId);

//    List<EtcTreeView> getEtcTreeview(Long memberId);

    Boolean getComplate(Long treeId);

    void delete(Long treeId);


//    TreeInfoView getTreeInfoViewByTreeId(Long treeId);

//    CombineTreeView findCombineTreeView(Long memberId, Long treeId);

    Boolean isOwner(Long treeId, Long memberId);
    Integer getTreeCount(Long id);
    Integer getLeafCount(Long treeId);
    Long getMemberId(Long treeId);

    Seed getSeedByTreeId(Long treeId);

//    List<TreeCardView> findTreeCardView(Long seedId, Long memberId);

}