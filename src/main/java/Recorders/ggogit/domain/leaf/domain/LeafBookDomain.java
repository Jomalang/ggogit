package Recorders.ggogit.domain.leaf.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class LeafBookDomain {
    private Long id;
    private Long treeId;
    private Long parentLeafId;
    private Long startPage;
    private Long endPage;
    private List<LeafTagDomain> tags;
    private String title;
    private String content;
    private Boolean visibility;
    private Long childLeafCount;
}