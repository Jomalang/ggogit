package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.structure.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBreadcrumbResponse {

    private String treeName;
    private String branchName;
    private String leafName;

    public static LeafBreadcrumbResponse of(Leaf leaf, TreeNode branch) {
        return LeafBreadcrumbResponse.builder()
                .treeName(leaf.getTree().getTitle())
                .branchName(branch.getValue().getTitle())
                .leafName(leaf.getTitle())
                .build();
    }
}