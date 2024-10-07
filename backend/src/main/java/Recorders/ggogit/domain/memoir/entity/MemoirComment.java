package Recorders.ggogit.domain.memoir.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoirComment {
    private Long id;
    @NotNull
    private Long memberId;
    @NotNull
    private Long memoirId;
    private Integer likeCount;
    private String content;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}

