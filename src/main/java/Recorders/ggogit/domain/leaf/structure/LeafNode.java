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
public class LeafNode {
    private Leaf data;
    private LeafNode parent;
    private List<Leaf> children;
    private LeafDirectionType direction;

    public LeafNode(Leaf data) {
        this.data = data;
        this.children = new ArrayList<>();
        this.direction = LeafDirectionType.INIT;
    }

    public void addChild(Leaf leaf) {
        children.add(leaf);
        if (parent == null) {
            this.direction = LeafDirectionType.START;
            return;
        } // root인 경우
        switch (children.size()) {
            case 1:
                direction = LeafDirectionType.DOWN;
                break;
            case 2:
                direction = LeafDirectionType.LEFT;
                break;
            case 3:
                direction = LeafDirectionType.SIDE;
                break;
//                throw new IllegalArgumentException("LeafNode의 children이 4개 이상입니다.");
        }
    }

    public boolean isBranch() {
        return data.getChildLeafCount() != 1;
    }

    public void setParent(LeafNode parent) {
        this.parent = parent;
        this.direction = LeafDirectionType.END;
    }
}