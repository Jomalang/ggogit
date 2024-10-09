package Recorders.ggogit.api.leaf.dto.request;

import Recorders.ggogit.domain.leaf.view.LeafBookView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBookFirstCreateRequest {

    @NotNull(message = "startPage를 입력해주세요.")
    private Integer startPage;

    @NotNull(message = "endPage를 입력해주세요.")
    private Integer endPage;

    @NotNull(message = "태그를 선택해주세요.")
    private List<Long> tagIds;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotNull(message = "visibility를 입력해주세요.")
    private Boolean visibility;

    public boolean isValidation() {
        boolean isValidation = true;

        // 태그 선택이 없을 경우
        if (tagIds == null || tagIds.isEmpty()) {
            throw new IllegalArgumentException("태그를 선택해주세요.");
        }

        // 읽은 페이지가 더 클 경우
        if (endPage < startPage) {
            throw new IllegalArgumentException("시작 페이지가 끝 페이지보다 큽니다.");
        }

        return isValidation;
    }

    public LeafBookView toLeafBookView() {
        return LeafBookView.builder()
                .leafId(null)
                .treeId(null)
                .parentLeafId(null)
                .tags(null)
                .startPage(startPage)
                .endPage(endPage)
                .tagIds(tagIds)
                .title(title)
                .content(content)
                .visibility(visibility)
                .childLeafCount(0L)
                .build();
    }
}