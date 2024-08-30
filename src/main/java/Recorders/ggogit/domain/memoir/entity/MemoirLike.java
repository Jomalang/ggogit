package Recorders.ggogit.domain.memoir.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemoirLike {
    @NotNull
    private long memberId;
    @NotNull
    private long memoirId;
    private boolean activate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
