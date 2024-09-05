package Recorders.ggogit.web.leaf.form;


import Recorders.ggogit.Type.SeedCategoryType;
import Recorders.ggogit.domain.leaf.view.LeafView;
import Recorders.ggogit.domain.leaf.view.LeafTagView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LeafForm {
    
    @NotNull
    private SeedCategoryType seed;

    @NotNull(message = "태그를 선택해주세요.")
    private List<Long> tagIds;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotNull
    private Boolean visibility;

    public void setSeed(@NotNull(message = "시드를 입력해주세요.") Long seed) {
        this.seed = SeedCategoryType.of(seed);
    }

    public LeafView toLeafView() {
            return LeafView.builder()
                    .tags(getTags())
                    .title(title)
                    .content(content)
                    .visibility(visibility)
                    .build();
    }

    public List<LeafTagView> getTags() {
        List<LeafTagView> tags = new ArrayList<>();
        for (Long tagId : tagIds) {
            tags.add(LeafTagView.builder().id(tagId).build());
        }
        return tags;
    }
}