
package Recorders.ggogit.domain.leaf.view;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafView {
    private Long id;
    private Long treeId;
    private Long parentLeafId;
    private List<LeafTag> tags;
    private String title;
    private String content;
    private Boolean visibility;
    private Long childLeafCount;
}