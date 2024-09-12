package Recorders.ggogit.web.leaf.form;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LeafTagForm {
    @Nullable
    private Long id;
    @NotBlank(message = "태그 이름을 입력해주세요.")
    private String name;

    public LeafTag toEntity() {
        return LeafTag.builder()
                .id(id)
                .name(name)
                .build();
    }
}