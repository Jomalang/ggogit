package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.api.tree.dto.TreeCardRequest;
import io.ggogit.ggogit.api.tree.dto.TreeInfoResponse;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TreeService  {

    void register(Tree tree);
    void update(Tree tree);
    void delete(Long treeId);
    Tree get(Long treeId);
    List<Tree> findAllByMemberId(Long memberId);

    Boolean getComplate(Long treeId);
    Boolean isOwner(Long treeId, Long memberId);
    Integer getTreeCount(Long id);
    Integer getLeafCount(Long treeId);
    Long getMemberId(Long treeId);

    Seed getSeedByTreeId(Long treeId);
    Page<TreeCardRequest> findTreeCardRequestList(Long seedId, Long memberId, Pageable pageable);

    Page<TreeInfoResponse> findTreeInfoResponseList(Long seedId, Long memberId, Pageable pageable);
    //TreeInfoDto 생성
//    List<TreeInfoResponse> findTreeInfoResponse(Long memberId);

//    List<TreeInfoView> getTreeInfoView(Long seedId, Long memberId);

//    List<BookTreeView> getBookTreeView(Long memberId);

//    List<EtcTreeView> getEtcTreeview(Long memberId);
//    TreeInfoView getTreeInfoViewByTreeId(Long treeId);

//    CombineTreeView findCombineTreeView(Long memberId, Long treeId);


}