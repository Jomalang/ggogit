package Recorders.ggogit.domain.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCommentLike {
    private Long memberId;
    private Long bookCommentId;
    private Boolean activate;
    private LocalDate updateTime;
    private LocalDate createTime;
}
