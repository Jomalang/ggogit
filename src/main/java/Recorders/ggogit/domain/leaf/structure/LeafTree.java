package Recorders.ggogit.domain.leaf.structure;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafTree {
    private LeafNode root;
    private long size;
    private List<LeafNode> nodes; // 모든 노드
    private List<LeafNode> branchNodes; // 분기 노드

    public LeafTree(List<Leaf> nodes) {
        this.size = nodes.size();
        this.nodes = new ArrayList<>();
        this.branchNodes = new ArrayList<>();

        for (Leaf node : nodes) {
            addNode(node);
        }
    }

    public void addNode(Leaf node) {
        LeafNode leafNode = new LeafNode(node);
        nodes.add(leafNode);

        if (root == null) { // root가 없으면 root로 설정
            root = leafNode;
            branchNodes.add(leafNode);
            return;
        }

        // 리프노드들 에서 부모 찾기
        LeafNode parent = findParent(node);
        leafNode.setParent(parent);
        if (!parent.isBranch()) { // 분기가 아니면 제거
            branchNodes.remove(parent);
        }

        parent.addChild(node);
        branchNodes.add(leafNode);
    }

    private LeafNode findParent(Leaf node) {
        return findParent(node.getParentLeafId());
    }

    private LeafNode findParent(Long leafId) {
        for (LeafNode parent : branchNodes) {
            if (parent.getData().getId().equals(leafId)) {
                return parent;
            }
        }
        throw new IllegalArgumentException("부모 노드가 없습니다.");
    }

    public List<Leaf> getBranch(Long leafId) {
        List<Leaf> branchLeafs = new ArrayList<>();
        LeafNode leafNode = findParent(leafId);

        do {
            branchLeafs.add(leafNode.getData());
            leafNode = leafNode.getParent();
        } while (leafNode != null);

        return branchLeafs;
    }

    public List<LeafNode> getBranchNodes(Long leafId) {
        List<LeafNode> branchNodes = new ArrayList<>();
        LeafNode leafNode = findParent(leafId);

        do {
            branchNodes.add(leafNode);
            leafNode = leafNode.getParent();
        } while (leafNode != null);

        return branchNodes;
    }
}