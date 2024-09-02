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

