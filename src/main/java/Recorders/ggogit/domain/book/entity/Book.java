package Recorders.ggogit.domain.book.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private Long id;
    private Long memberId;
    private Long bookCategoryId;
    private Long totalPage;
    private String title;
    private String author;
    private String publisher;
    private String imageFile;
    private Boolean resourceFrom;
    private LocalDateTime publicDate;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
