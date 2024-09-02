package Recorders.ggogit.domain.tree.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Repository
public class Vm_FindTreeInfo {
    private Long bookId;
    private Long treeId;
    private Long memberId ;
    private Long seedId;
    private String title;
    private String description;
    private Integer bookMarkCountNum;
    private Boolean visibility;
    private Date updateTime;
    private Date createTime;
    private Integer readingPage ;
    private String treeImageName;
    private Long treeLikeCnt;

}
