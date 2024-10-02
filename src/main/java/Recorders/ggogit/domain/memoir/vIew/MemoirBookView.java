package Recorders.ggogit.domain.memoir.vIew;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoirBookView {

    //회고록
    private Long id;
    private Long treeId;
    private String title;
    private LocalDateTime updateTime;
    private Boolean visibility;


    //도서
    private Long bookId;
    private String bookCategory; //Book entity와 자료형 다름
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator; //Book entity에 없음
    private String bookPublisher;
    private Integer bookPublishedYear;
    private Boolean bookComplete;


    //조회수, 리프
    private Long views;
    private Long leafNums;
}
