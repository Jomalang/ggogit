package Recorders.ggogit.domain.tree.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tree {
    private long id;
    private long memberId ;
    private long seedId;
    private String title;
    private String description;
    private int bookMarkCountNum;
    private boolean visibility;
}
