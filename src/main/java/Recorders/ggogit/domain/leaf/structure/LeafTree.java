package Recorders.ggogit.domain.leaf.structure;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
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

        if (root == null) { // root가 없으면 root로 설정
            root = leafNode;
            nodes.add(leafNode);
            branchNodes.add(leafNode);
            return;
        }

        LeafNode parent = findParent(node); // 리프노드들 에서 부모 찾기
        leafNode.setParent(parent); // 부모 설정
        if (!parent.isBranch()) { // 트리 자식 개수가 3개 이상이면, 분기 못함 > 분기 목록에서 제거
            branchNodes.remove(parent);
        }

        parent.addChild(leafNode); // 부모에 자식 추가
        branchNodes.add(leafNode); // 분기 목록에 추가
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
        System.out.println("leafId = " + leafId);
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

    public List<LeafNode> findAll(Long leafId) {
        List<LeafNode> branchNodes = new ArrayList<>();
        findToEnd(leafId, branchNodes); // INPUT 리프 부터 말단 리프까지
        findToRoot(leafId, branchNodes); // INPUT 리프 부터 ROOT까지
        branchNodes.sort(Comparator.comparing(o -> o.getData().getId())); // 생성 시간 순으로 정렬
        return branchNodes;
    }

    public List<LeafNode> findToEnd(Long leafId) {
        List<LeafNode> branchNodes = new ArrayList<>();
        branchNodes.add(findParent(leafId));
        findToEnd(leafId, branchNodes); // INPUT 리프 부터 말단 리프까지
        return branchNodes;
    }

    private void findToEnd(Long leafId, List<LeafNode> branchNodes) {
        LeafNode startEndNode = findParent(leafId);

        while (startEndNode.hasChildren()) { // INPUT 리프 부터 말단 리프까지
            List<LeafNode> children = startEndNode.getChildren();
            int size = children.size();
            if (size == 1) {
                startEndNode = children.getFirst();
            } else if (size == 2) {
                startEndNode = children.getFirst();
            } else if (size == 3) {
                startEndNode = children.get(1);
            }
            branchNodes.add(startEndNode);
        }
    }

    public List<LeafNode> findToRoot(Long leafId) {
        List<LeafNode> branchNodes = new ArrayList<>();
        findToRoot(leafId, branchNodes); // INPUT 리프 부터 ROOT까지
        return branchNodes;
    }

    private void findToRoot(Long leafId, List<LeafNode> branchNodes) {
        LeafNode startRootNode = findParent(leafId);
        while (startRootNode != null) { // INPUT 리프 부터 ROOT까지
            branchNodes.addLast(startRootNode);
            startRootNode = startRootNode.getParent();
        }
    }
}