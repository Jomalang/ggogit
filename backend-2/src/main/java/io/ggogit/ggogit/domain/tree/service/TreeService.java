package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.api.tree.dto.*;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TreeService  {

    void register(Tree tree);
    void update(Tree tree);
    void delete(Long treeId);
    Tree get(Long treeId);
    List<Tree> findAllByMemberId(Long memberId);


    Page<Tree> findAllPages(Long memberId, Pageable pageable);

    Boolean getComplate(Long treeId);
    Boolean isOwner(Long treeId, Long memberId);
    Boolean findTreeByTreeId(Long treeId);

    Integer getTreeCount(Long id);
    Integer getLeafCount(Long treeId);
    Long getMemberId(Long treeId);

    Seed getSeedByTreeId(Long treeId);

    TreeInfoResponse findTreeInfoResponse(Long memberId, Long treeId);

    Page<TreeInfoResponse> findTreeInfoResponseList(Long memberId, Pageable pageable);

    List<TreeInfoResponse> findTreeInfoResponseList(Long memberId);
    TreeListHome findTreeInfoResponseList(Long memberId, Long seedId, int page);

    Page<Tree> findAllByBookId(Long memberId, Long bookId);
    Page<Tree> findAllCardByBookId(Long BookId, int page, int size);
    Page<Tree> findTreeByQueryAndMemberId(@Valid TreeSearchQuery query, Long memberId);

    String findTreeImageName(Long treeId);

    void editEtcTree(TreeEtcEdiitRequest dto, Long treeId);

    void updateAutoTreeBook(TreeBookEdiitRequest dto, Long treeId);

    void updateManualTreeBook(TreeBookEdiitRequest dto, Long treeId);

    //TreeInfoDto 생성
//    List<TreeInfoResponse> findTreeInfoResponse(Long memberId);

//    List<TreeInfoView> getTreeInfoView(Long seedId, Long memberId);

//    List<BookTreeView> getBookTreeView(Long memberId);

//    List<EtcTreeView> getEtcTreeview(Long memberId);
//    TreeInfoView getTreeInfoViewByTreeId(Long treeId);

//    CombineTreeView findCombineTreeView(Long memberId, Long treeId);


}