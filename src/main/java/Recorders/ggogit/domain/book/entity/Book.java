package Recorders.ggogit.domain.book.entity;

import lombok.*;

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
    private String translator;
    private String isbn;
    private String publisher;
    private String imageFile;
    private LocalDateTime publishDate;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
