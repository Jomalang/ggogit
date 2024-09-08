package Recorders.ggogit.domain.tree.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vm_TreeLikeCount {
    private Long treeId;
    private Integer count;

}
