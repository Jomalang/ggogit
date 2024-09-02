package Recorders.ggogit.domain.memoir.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoirCommentLike {

    @NotNull
    private Long memberId;

    @NotNull
    private Long memoirCommentId;

    private Boolean activate;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
