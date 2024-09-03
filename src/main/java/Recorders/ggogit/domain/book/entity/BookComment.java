package Recorders.ggogit.domain.book.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookComment {
    private Long id;
    private Long memberId;
    private Long bookId;
    private Long likeCount;
    private String content;
    private LocalDate updateTime;
    private LocalDate createTime;
}
