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
public class Tree {
    private long id;
    private long memberId ;
    private long seedId;
    private String title;
    private String description;
    private int bookMarkCountNum;
    private boolean visibility;
    private Date updateTime;
    private Date createTime;
}
