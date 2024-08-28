
package Recorders.ggogit.domain.leaf.domain;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LeafEtcDomain {
    private Long id;
    private Long treeId;
    private Long parentLeafId;
    private List<TagDomain> tags;
    private String title;
    private String content;
    private Boolean visibility;
    private Long childLeafCount;
}