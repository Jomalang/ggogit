package Recorders.ggogit.domain.tree.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeBook {
    private Long treeId;
    private Integer readingPage;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}