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
public class LeafEtcFirstCreateRequest {

    @NotNull(message = "태그를 선택해주세요.")
    private List<Long> tagIds;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotNull(message = "visibility를 입력해주세요.")
    private Boolean visibility;

    public void isValidation() {
        if (tagIds == null || tagIds.isEmpty()) {
            throw new IllegalArgumentException("태그를 선택해주세요.");
        }
    }

    public LeafEtcView toLeafEtcView() {
        return LeafEtcView.builder()
                .leafId(null)
                .treeId(null)
                .parentLeafId(null)
                .tags(null)
                .title(title)
                .content(content)
                .visibility(visibility)
                .childLeafCount(0L)
                .build();
    }
}