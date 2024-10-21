package io.ggogit.ggogit.domain.leaf.structure;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.type.LeafDirectionType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeNode {
    private static final int MAX_CHILDREN = 3;
    private Leaf value;
    private TreeNode parent;
    private List<TreeNode> children;
    private LeafDirectionType direction;

    public TreeNode(Leaf value) {
        this.value = value;
        this.children = new ArrayList<>();
        this.direction = LeafDirectionType.INIT;
    }

    public void addChild(TreeNode child) {
        children.add(child);
        if (parent == null) { // root인 경우
            switch (children.size()) { // 부모가 없고 자식은 있는 경우
                case 1:
                    direction = LeafDirectionType.START_DOWN;
                    break;
                case 2:
                    direction = LeafDirectionType.START_RIGHT;
                    break;
                case 3:
                    direction = LeafDirectionType.START_SIDE;
                    break;
            }
            return;
        }

        // 부모가 있는 경우
        switch (children.size()) {
            case 1:
                direction = LeafDirectionType.DOWN;
                break;
            case 2:
                direction = LeafDirectionType.RIGHT;
                break;
            case 3:
                direction = LeafDirectionType.SIDE;
                break;
            default:
                throw new IllegalArgumentException("자식 노드가 3개 이상입니다.");
        }
    }

    public boolean isBranchPoint() {
        return children.size() < MAX_CHILDREN; // 트리 자식 개수
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
        this.direction = LeafDirectionType.END_UP;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }
}