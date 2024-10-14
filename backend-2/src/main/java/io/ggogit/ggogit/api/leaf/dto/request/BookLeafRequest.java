package io.ggogit.ggogit.api.leaf.dto.request;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLeafRequest {

    @NotNull(message = "startPage는 필수값입니다.")
    @Min(value = 1, message = "startPage는 1 이상이어야 합니다.")
    private Integer startPage;

    @NotNull(message = "endPage는 필수값입니다.")
    private Integer endPage;

    private List<Long> tagIds;

    @NotBlank(message = "title은 필수값입니다.")
    private String title;

    @NotBlank(message = "content는 필수값입니다.")
    private String content;

    @NotNull(message = "visibility는 필수값입니다.")
    private Boolean visibility;

    public void isValidate() {

        if (endPage < startPage) {
            throw new IllegalArgumentException("endPage는 startPage보다 작을 수 없습니다.");
        }

        if (tagIds == null) {
            tagIds = List.of();
        }
    }

    public Leaf toLeaf() {
        return Leaf.builder()
                .title(title)
                .content(content)
                .visibility(visibility)
                .build();
    }

    public LeafBook toLeafBook() {
        return LeafBook.builder()
                .startPage(startPage)
                .endPage(endPage)
                .build();
    }
}