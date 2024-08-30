package Recorders.ggogit.domain.memoir.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemoirCommentLike {

    @NotNull
    private long memberId;

    @NotNull
    private long memoirCommentId;

    private boolean activate;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
