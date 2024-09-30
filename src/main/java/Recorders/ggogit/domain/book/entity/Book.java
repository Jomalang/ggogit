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
    private String isbn;
    private String publisher;
    private String imageFile;
    private Boolean resourceFrom; // 도서 등록 여부 (true: API, false: 직접 등록)
    private LocalDateTime publishDate;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
