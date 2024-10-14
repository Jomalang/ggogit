package io.ggogit.ggogit.api.leaf.dto.request;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
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
public class EtcLeafRequest {

    private List<Long> tagIds;

    @NotBlank(message = "title은 필수값입니다.")
    private String title;

    @NotBlank(message = "content는 필수값입니다.")
    private String content;

    @NotNull(message = "visibility는 필수값입니다.")
    private Boolean visibility;

    public Leaf toLeaf() {
        return Leaf.builder()
                .title(title)
                .content(content)
                .visibility(visibility)
                .build();
    }

    public void isValidate() {
        if (tagIds == null) {
            tagIds = List.of();
        }
    }
}