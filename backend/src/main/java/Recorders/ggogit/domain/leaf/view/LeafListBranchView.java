package Recorders.ggogit.domain.leaf.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafListBranchView {
    String branchName;
    Long leafCount;
    Long likeCount;
    Long viewCount;
    LocalDateTime updateTime;

    public Date getUpdateTime() {
        return Date.from(updateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }
}
