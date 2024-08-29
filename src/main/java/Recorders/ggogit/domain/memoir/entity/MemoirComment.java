package Recorders.ggogit.domain.memoir.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemoirComment {
    private long id;
    @NotNull
    private long memberId;
    @NotNull
    private long memoirId;
    private int likeCount;
    private String content;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}

