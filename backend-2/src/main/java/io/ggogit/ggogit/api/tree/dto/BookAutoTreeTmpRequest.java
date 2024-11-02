package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookAutoTreeTmpRequest {
    // 도서 정보 (Book Tree인 경우 사용)
    @NotNull(message = "도서 정보를 입력해 주세요.")
    private Long bookId;

    // 트리 정보
    @NotBlank(message = "트리 제목을 입력해 주세요.")
    private String treeTitle;
    @NotBlank(message = "트리 설명을 입력해 주세요.")
    private String description;
    @NotNull(message = "공개여부를 설정해주세요.")
    private Boolean visibility;

    public TreeTmp toTreeTmp() {
        return TreeTmp.builder()
                .treeTitle(treeTitle)
                .description(description)
                .visibility(visibility)
                .build();
    }
}
