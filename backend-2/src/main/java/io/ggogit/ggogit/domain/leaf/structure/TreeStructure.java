package io.ggogit.ggogit.domain.leaf.structure;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeStructure {
    private TreeNode root;
    private long size;
    private List<TreeNode> nodes; // 모든 노드
    private Set<TreeNode> branchNodes; // 분기 노드

    public TreeStructure(List<Leaf> nodes) {
        this.size = nodes.size();
        this.nodes = new ArrayList<>();
        this.branchNodes = new HashSet<>();

        for (Leaf node : nodes) {
            addNode(node);
        }
    }

    private void addNode(Leaf node) {
        TreeNode treeNode = new TreeNode(node);

        if (root == null) { // root가 없으면 root로 설정
            root = treeNode;
            nodes.add(treeNode);
            branchNodes.add(treeNode);
            return;
        }

        TreeNode parent = findParent(node); // 리프노드들 에서 부모 찾기
        treeNode.setParent(parent); // 부모 설정
        if (!parent.isBranchPoint()) { // 트리 자식 개수가 3개 이상이면, 분기 못함 > 분기 목록에서 제거
            branchNodes.remove(parent);
        }

        parent.addChild(treeNode); // 부모에 자식 추가
        branchNodes.add(treeNode); // 분기 목록에 추가
    }

    private TreeNode findParent(Leaf node) {
        return findParent(node.getParentLeaf().getId());
    }

    private TreeNode findParent(Long leafId) {
        for (TreeNode parent : branchNodes) {
            if (parent.getValue().getId().equals(leafId)) {
                return parent;
            }
        }
        throw new IllegalArgumentException("부모 노드를 찾을 수 없습니다.");
    }

    public List<TreeNode> findToEnd(Long leafId, boolean isOwner) {
        List<TreeNode> branch = new ArrayList<>();
        TreeNode node = findParent(leafId);

        if (!isOwner && !node.getValue().getVisibility()) {
            leafNoVisibility(node);
        }

        branch.add(node);
        findToEnd(leafId, branch, isOwner);
        return branch;
    }

    private void findToEnd(Long leafId, List<TreeNode> branch, boolean isOwner) {
        TreeNode node = findParent(leafId); // 부모 노드 찾기

        while (node.hasChildren()) { // INPUT 리프 부터 말단 리프까지
            List<TreeNode> children = node.getChildren();
            int size = children.size();
            if (size == 1) {
                node = children.getFirst();
            } else if (size == 2) {
                node = children.getFirst();
            } else if (size == 3) {
                node = children.get(1);
            }

            if (!isOwner && !node.getValue().getVisibility()) { // 비공개 리프 노출 X
                leafNoVisibility(node);
            }

            branch.add(node);
        }
    }

    /**
     *  나의 부모 리프부터 ROOT까지 찾기
     * @param leafId
     * @param isOwner
     */
    public List<TreeNode> findToRoot(Long leafId, boolean isOwner) {
        List<TreeNode> branch = new ArrayList<>();
        findToRoot(leafId, branch, isOwner);
        return branch;
    }

    private void findToRoot(Long leafId, List<TreeNode> branch, boolean isOwner) {
        TreeNode node = findParent(leafId); // 부모 노드 찾기
        while (node != null) {

            if (!isOwner && !node.getValue().getVisibility()) {
                leafNoVisibility(node);
            }

            branch.addLast(node);
            node = node.getParent();
        }
    }

    private void leafNoVisibility(TreeNode node) {
        Leaf leaf = node.getValue();
        leaf.setTitle("비공개 리프입니다.");
        leaf.setCreateTime(null);
    }

    public List<TreeNode> findAll(Long leafId, boolean isOwner) {
        List<TreeNode> branchNodes = new ArrayList<>();
        findToEnd(leafId, branchNodes, isOwner); // INPUT 리프 부터 말단 리프까지
        findToRoot(leafId, branchNodes, isOwner); // INPUT 리프 부터 ROOT까지
        branchNodes.sort(Comparator.comparing(o -> o.getValue().getId())); // 생성 시간 순으로 정렬
        return branchNodes;
    }
}