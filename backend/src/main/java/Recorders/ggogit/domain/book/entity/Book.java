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
    private String title;
    private String author;  // 알라딘 api 에서는 작가와 옮긴이가 author 에 같이 나옵니다
    private String translator;  // 아무도 옮긴이를 궁금해 하지 않을 것 같습니다
    private String isbn;
    private String publisher;
    private Date publishDate; // 출판일 기본 로컬데이트에서 데이트로 변경 < 이거때문에 TreeSaveTmp 에서 주석처리한 부분 있음
    private Long totalPage; // todo ItemLookUp url 요청을 이용하여 isbn13 으로 itemPage 값을 반복문을 이용해 하나하나 받아와야함
    private String imageFile;
    private boolean isDeleted;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
