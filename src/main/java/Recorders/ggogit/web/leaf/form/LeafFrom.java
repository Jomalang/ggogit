package Recorders.ggogit.web.leaf.form;


import Recorders.ggogit.type.SeedCategoryType;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import Recorders.ggogit.domain.leaf.view.LeafView;
import Recorders.ggogit.domain.leaf.view.LeafTagView;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LeafFrom {

    private SeedCategoryType seed;

    @Nullable // etc Leaf 등록시 null
    private Long startPage;

    @Nullable // etc Leaf 등록시 null
    private Long endPage;

    @NotNull(message = "태그를 선택해주세요.")
    private List<Long> tagIds;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private Boolean visibility;

    public void setSeed(@NotNull(message = "시드를 입력해주세요.") Integer seed) {
        this.seed = SeedCategoryType.of(seed);
    }

    public LeafBookView toLeafBookView() {
        return LeafBookView.builder()
                .startPage(startPage)
                .endPage(endPage)
                .tags(getTags())
                .title(title)
                .content(content)
                .visibility(visibility)
                .build();
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