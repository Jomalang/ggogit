package Recorders.ggogit.domain.tree.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeLike {
    private long memberId;
    private long treeId;
    private boolean activate ;
    private Date updateTime;
    private Date createTime;
}
