package Recorders.ggogit.domain.memoir.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemoirCommentLikeView {
    private long id;
    private long treeId;
    private String title;
    private int commentCnt;
    private int likeCnt;
}
