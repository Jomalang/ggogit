package Recorders.ggogit.domain.leaf.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafDetailView {
    String title;
    String content;
    Integer pageStart;
    Integer pageEnd;
}
