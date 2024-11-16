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
    private Integer statusCode;

    public static TreeTmpResponse of(Long id, String message, Integer statusCode) {
        return  TreeTmpResponse.builder()
                .treeTmpId(id)
                .message(message)
                .statusCode(statusCode)
                .build();
    }

    public static TreeTmpResponse of(TreeTmp treeTmp, String message, Integer statusCode) {
        return  TreeTmpResponse.builder()
                .treeTmpId(treeTmp.getId())
                .message(message)
                .statusCode(statusCode)
                .build();
    }
}