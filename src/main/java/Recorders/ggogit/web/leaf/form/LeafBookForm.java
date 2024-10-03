package Recorders.ggogit.web.leaf.form;


import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import Recorders.ggogit.domain.leaf.view.LeafEtcView;
import Recorders.ggogit.domain.leaf.view.LeafView;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LeafBookForm {

    private Long leafId;

    private Long seedId;

    private Long parentId;

    private Long treeId;

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

    public LeafBookView toLeafBookView() {
        return LeafBookView.builder()
                .leafId(leafId)
                .treeId(treeId)
                .parentLeafId(parentId)
                .startPage(startPage)
                .endPage(endPage)
                .tags(getTags())
                .title(title)
                .content(content)
                .visibility(visibility)
                .build();
    }

    public LeafEtcView toLeafEtcView() {
        return LeafEtcView.builder()
                .treeId(treeId)
                .leafId(leafId)
                .parentLeafId(parentId)
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

    public List<LeafTag> getTags() {
        List<LeafTag> tags = new ArrayList<>();
        for (Long tagId : tagIds) {
            tags.add(LeafTag.builder().id(tagId).build());
        }
        return tags;
    }
}