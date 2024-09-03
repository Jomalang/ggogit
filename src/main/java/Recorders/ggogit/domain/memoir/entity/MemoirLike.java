package Recorders.ggogit.domain.memoir.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoirLike {
    @NotNull
    private Long memberId;
    @NotNull
    private Long memoirId;
    private Boolean activate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
