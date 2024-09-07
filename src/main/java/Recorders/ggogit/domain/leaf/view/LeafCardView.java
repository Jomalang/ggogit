package Recorders.ggogit.domain.leaf.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafCardView {
    private Long treeId;
    private Long leafId;
    private String title;
    private String content;
    private Long likeCount;
    private Long viewCount;
    private String userNickName;
    private String userEmailId;
    private Boolean likeActive;
    private LocalDateTime updateTime;
}