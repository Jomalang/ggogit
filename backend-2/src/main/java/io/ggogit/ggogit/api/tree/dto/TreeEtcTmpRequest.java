package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeEtcTmpRequest {
    private Long memberId;
    private Long seedId;
    private String treeTitle;
    private String description;
    private String imageFile;
    private Boolean visibility;

    public TreeTmp toTreeTmp() {
        return TreeTmp.builder()
                .treeTitle(treeTitle)
                .description(description)
                .imageFile(imageFile)
                .visibility(visibility)
                .build();
    }
}
