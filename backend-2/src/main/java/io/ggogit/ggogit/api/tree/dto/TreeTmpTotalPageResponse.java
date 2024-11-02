package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeTmpTotalPageResponse {

    private int totalPage;

    public static TreeTmpTotalPageResponse of(TreeTmp treeTmp){
        return TreeTmpTotalPageResponse.builder()
                .totalPage(treeTmp.getTotalPage())
                .build();
    }
}