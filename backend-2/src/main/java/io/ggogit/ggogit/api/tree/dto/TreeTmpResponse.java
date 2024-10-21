package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeTmpResponse {
    private Long treeTmpId;
    private String message;

    public static TreeTmpResponse of(Long id, String message) {
        return  TreeTmpResponse.builder()
                .treeTmpId(id)
                .message(message)
                .build();
    }
    public static TreeTmpResponse of(TreeTmp treeTmp, String message) {
        return  TreeTmpResponse.builder()
                .treeTmpId(treeTmp.getId())
                .message(message)
                .build();
    }
}
