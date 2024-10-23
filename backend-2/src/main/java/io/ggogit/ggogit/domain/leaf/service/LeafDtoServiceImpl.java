package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchInfoResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafBranchResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafItemResponse;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.leaf.entity.LeafTagMap;
import io.ggogit.ggogit.domain.leaf.repository.LeafRepository;
import io.ggogit.ggogit.domain.leaf.repository.LeafTagMapRepository;
import io.ggogit.ggogit.domain.leaf.structure.TreeNode;
import io.ggogit.ggogit.domain.leaf.structure.TreeStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeafDtoServiceImpl implements LeafDtoService {

    private final LeafRepository leafRepository;
    private final LeafTagMapRepository leafTagMapRepository;

    @Override
    @Transactional(readOnly = true)
    public LeafItemResponse getLeafNodeRootToEnd(Long leafId, boolean isOwner) {

        // 트리 아이디 기반 리프들 조회
        List<Leaf> leafNodes = getTreeLeafs(leafId);

        // 트리 생성
        TreeStructure treeStructure = new TreeStructure(leafNodes);

        // 트리에서 Root To End 까지 조회
        List<TreeNode> treeNodes = treeStructure.findAll(leafId, isOwner);

        // LeafItemResponse 로 변환
        return getLeafItemResponse(treeNodes);
    }

    @Override
    public LeafBranchInfoResponse getBranchInfo(Long leafId) {

        // 트리 아이디 기반 리프들 조회
        List<Leaf> leafNodes = getTreeLeafs(leafId);

        // 트리 생성
        TreeStructure treeStructure = new TreeStructure(leafNodes);
        List<TreeNode> treeNodes = treeStructure.findAll(leafId, true); // 브랜치 정보 조회는 비공개 리프도 조회

        int likeCount = 0;
        int viewCount = 0;
        for (TreeNode treeNode : treeNodes) {
            Leaf leaf = treeNode.getValue();
            likeCount += leaf.getLikeCount();
            viewCount += leaf.getViewCount();
        }

        return LeafBranchInfoResponse.builder()
                .branchName(treeNodes.getLast().getValue().getTitle())
                .leafCount(treeNodes.size())
                .likeCount(likeCount)
                .viewCount(viewCount)
                .updateTime(treeNodes.getLast().getValue().getUpdateTime())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public LeafItemResponse getLeafNodeToEnd(Long leafId, boolean isOwner) {
        List<Leaf> leafNodes = getTreeLeafs(leafId);
        TreeStructure treeStructure = new TreeStructure(leafNodes);
        List<TreeNode> treeNodes = treeStructure.findToEnd(leafId, isOwner);
        return getLeafItemResponse(treeNodes);
    }


    @Override
    public HashMap<String, Integer> nodeCountToRoot(Leaf leaf) {
        HashMap<String, Integer> result = new HashMap<>();
        Integer likeCnt = 0;
        Integer viewCnt = 0;
        Integer leafCnt = 1;
        Leaf parentLeaf, tmpLeaf;

        do {
            likeCnt += leaf.getLikeCount();
            viewCnt += leaf.getViewCount();
            leafCnt++;
            tmpLeaf = leaf;
            parentLeaf = leaf.getParentLeaf();
            leaf = parentLeaf;
        } while (!(tmpLeaf.getParentLeaf() == null));

        result.put("like", likeCnt);
        result.put("view", viewCnt);
        result.put("leaf", leafCnt);

        return result;
    }

    @Override
    public List<LeafBranchResponse> findBranchByFilter(Long treeId, Boolean owner, Boolean bookMark) {
        System.out.println("treeId = " + treeId);
        List<Leaf> leafList = leafRepository.findByBranchQuery(treeId, owner, bookMark);

        List<LeafBranchResponse> responseList = new ArrayList<>();
        for (Leaf leaf : leafList) {
            HashMap<String, Integer> result = nodeCountToRoot(leaf);

            Integer likeCnt = result.get("like");
            Integer viewCnt = result.get("view");
            Integer leafCnt = result.get("leaf");

            responseList.add(LeafBranchResponse.of(leaf, likeCnt, viewCnt, leafCnt));
        }


        for(LeafBranchResponse leaf : responseList){
            System.out.println(leaf.toString());
        }

        return responseList;
    }

    private LeafItemResponse getLeafItemResponse(List<TreeNode> treeNodes) {
        LeafItemResponse response = new LeafItemResponse();
        for (TreeNode treeNode : treeNodes) {
            // 리프 노드의 태그 조회
            List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(treeNode.getValue());
            List<LeafTag> leafTags = new ArrayList<>();
            for (LeafTagMap leafTagMap : leafTagMaps) {
                leafTags.add(leafTagMap.getLeafTag());
            }
            response.addItem(treeNode, leafTags);
        }

        return response;
    }

    private List<Leaf> getTreeLeafs(Long leafId) {
        // 리프 조회
        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        // 트리 아이디 기반 리프들 조회
        return leafRepository.findByTreeOrderById(leaf.getTree());
    }
}