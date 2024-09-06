package Recorders.ggogit.domain.leaf.view;


import Recorders.ggogit.Type.LeafDirectionType;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafItemView {
    private Long id;
    private Long parentLeafId;
    private String title;
    private List<LeafTag> tags;
    private Boolean focused;
    private LeafDirectionType direction;
    private LocalDateTime createTime;

    public Date getCreateTime() {
        return Date.from(createTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }
}