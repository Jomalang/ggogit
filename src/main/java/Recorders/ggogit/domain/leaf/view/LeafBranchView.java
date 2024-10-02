package Recorders.ggogit.domain.leaf.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafBranchView {
    Long id;
    String title;
    Long likeCount;
    Long viewCount;
    Boolean bookMark;
    Date updateTime;
}
