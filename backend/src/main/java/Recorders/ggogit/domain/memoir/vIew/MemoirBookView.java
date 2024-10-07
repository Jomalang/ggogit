package Recorders.ggogit.domain.memoir.vIew;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String coverPath;
    private String bookCategory; //Book entity와 자료형 다름
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator; //Book entity에 없음
    private String bookPublisher;
    private LocalDate bookPublishedYear;
    private Boolean bookComplete;


    //조회수, 리프
    private Long views;
    private Long leafNums;

    public String getBookPublishedYear() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return this.bookPublishedYear.format(formatter);
    }

    public String getUpdateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        return this.updateTime.format(formatter);
    }
}
