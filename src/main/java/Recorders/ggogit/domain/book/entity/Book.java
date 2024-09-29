package Recorders.ggogit.domain.book.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private Long id;
    private Long memberId;
    private Long bookCategoryId;
    private Long totalPage; // // 알라딘에서 책의 총 페이지 수 확인을 지원 안함
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String imageFile;
    private Boolean resourceFrom; // 도서 등록 여부 (true: API, false: 직접 등록)
    private Date publishDate; // 출판일 기본 로컬데이트에서 데이트로 변경 < 이거때문에 TreeSaveTmp 에서 주석처리한 부분 있음
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
