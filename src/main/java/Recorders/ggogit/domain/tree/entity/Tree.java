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
    private Long id;
    private Long memberId ;
    private Long seedId;
    private String title;
    private String description;
    private Integer bookMarkCountNum;
    private Boolean visibility;
    private Date updateTime;
    private Date createTime;
}
