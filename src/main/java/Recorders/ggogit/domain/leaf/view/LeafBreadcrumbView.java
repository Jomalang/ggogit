package Recorders.ggogit.domain.leaf.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBreadcrumbView {
    String treeName;
    String branchName; // 브랜치 명
}