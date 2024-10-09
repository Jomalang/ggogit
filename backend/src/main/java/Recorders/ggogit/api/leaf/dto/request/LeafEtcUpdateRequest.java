package Recorders.ggogit.api.leaf.dto.request;

import Recorders.ggogit.domain.leaf.view.LeafEtcView;
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
public class LeafEtcUpdateRequest {

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

        return isValidation;
    }

    public LeafEtcView toLeafEtcView() {
        return LeafEtcView.builder()
                .leafId(null)
                .treeId(null)
                .parentLeafId(null)
                .title(title)
                .content(content)
                .visibility(visibility)
                .tagIds(tagIds)
                .tags(null)
                .build();
    }
}