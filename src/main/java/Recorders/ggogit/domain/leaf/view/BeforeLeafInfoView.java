package Recorders.ggogit.domain.leaf.view;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeforeLeafInfoView {
    private Long leafId;
    private String title;
    private String createDate;
    private List<LeafTag> tags;

    public static BeforeLeafInfoView of(Leaf leaf, List<LeafTag> leafTags) {
        return BeforeLeafInfoView.builder()
                .leafId(leaf.getId())
                .title(leaf.getTitle())
                .createDate(leaf.getCreateTime().toLocalDate().toString())
                .tags(leafTags)
                .build();
    }
}
