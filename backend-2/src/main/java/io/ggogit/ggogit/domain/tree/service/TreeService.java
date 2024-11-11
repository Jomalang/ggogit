package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.api.tree.dto.TreeCardRequest;
import io.ggogit.ggogit.api.tree.dto.TreeInfoResponse;
import io.ggogit.ggogit.api.tree.dto.TreeSearchQuery;
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
    Integer getTreeCount(Long id);
    Integer getLeafCount(Long treeId);
    Long getMemberId(Long treeId);

    Seed getSeedByTreeId(Long treeId);

    TreeInfoResponse findTreeInfoResponse(Long memberId, Long treeId);

    Page<TreeInfoResponse> findTreeInfoResponseList(Long memberId, Pageable pageable);

    List<TreeInfoResponse> findTreeInfoResponseList(Long memberId);
    List<TreeInfoResponse> findTreeInfoResponseList(Long memberId, Long seedId);

    Page<Tree> findTreeByQueryAndMemberId(@Valid TreeSearchQuery query, Long memberId);

    //TreeInfoDto 생성
//    List<TreeInfoResponse> findTreeInfoResponse(Long memberId);

//    List<TreeInfoView> getTreeInfoView(Long seedId, Long memberId);

//    List<BookTreeView> getBookTreeView(Long memberId);

//    List<EtcTreeView> getEtcTreeview(Long memberId);
//    TreeInfoView getTreeInfoViewByTreeId(Long treeId);

//    CombineTreeView findCombineTreeView(Long memberId, Long treeId);


}