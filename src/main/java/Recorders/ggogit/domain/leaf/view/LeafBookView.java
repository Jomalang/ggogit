package Recorders.ggogit.domain.leaf.view;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBookView {
    private Long id;
    private Long treeId;
    private Long parentLeafId;
    private Long startPage;
    private Long endPage;
    private List<LeafTagView> tags;
    private String title;
    private String content;
    private Boolean visibility;
    private Long childLeafCount;
}