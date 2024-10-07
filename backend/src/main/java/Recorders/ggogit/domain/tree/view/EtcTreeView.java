package Recorders.ggogit.domain.tree.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtcTreeView {
    private Long treeId;
    private Long memberId ;
    private Long seedId;
    private String title;
    private String description;
    private Boolean visibility;
    private Date leafCreatedAt;
    private Date createdAt;
    private String coverImageName;
}
