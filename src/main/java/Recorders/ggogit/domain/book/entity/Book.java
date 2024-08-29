package Recorders.ggogit.domain.book.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private Long id;
    private Long memberId;
    private Long totalPage;
    private String title;
    private String author;
    private String publisher;
    private String imageFile;
    private Boolean resourceFrom;
    private LocalDate publicDate;
    private LocalDate updateTime;
    private LocalDate createTime;
}
