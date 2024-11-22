package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeEtcEdiitRequest {
    private Long memberId;
    private Long treeId;
    private Long seedId;
    private String treeTitle;
    private String description;
    private String imageFile;
    private Boolean visibility;
}
