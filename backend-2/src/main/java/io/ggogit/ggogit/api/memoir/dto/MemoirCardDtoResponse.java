package io.ggogit.ggogit.api.memoir.dto;

import io.ggogit.ggogit.api.tree.dto.TreeInfoResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class MemoirCardDtoResponse {

    //회고록
    private Long id;
    private Long treeId;
    private String title;
    private String updateTime;
    private Boolean visibility;


    //도서
    private Long bookId;
    private String bookCoverPath;
    private String bookCategory; //Book entity와 자료형 다름
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator; //Book entity에 없음
    private String bookPublisher;
    private String bookPublishedYear;

    //조회수, 리프
    private Long views;
    private Long leafNums;

    public static String changeBookPublishedYear(LocalDate bookPublishedYear) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return bookPublishedYear.format(formatter);
    }

    public static String changeUpdateTime(LocalDateTime updateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        return updateTime.format(formatter);
    }

    //views와 leafNums는 Dto생성 이후 클라이언트 코드가 나중에 추가해야함!!!!!
    public static MemoirCardDtoResponse of(Memoir memoir, Tree tree) {
        return MemoirCardDtoResponse.builder()
                .id(memoir.getId())
                .treeId(memoir.getTree().getId())
                .title(memoir.getTitle())
                .updateTime(MemoirCardDtoResponse.changeUpdateTime(memoir.getUpdateTime()))
                .visibility(memoir.getVisibility())
                .bookId(tree.getBook().getId())
                .bookCoverPath(tree.getBook().getImageFile())
                .bookCategory(tree.getBook().getBookCategory().getName())
                .bookTitle(tree.getBook().getTitle())
                .bookAuthor(tree.getBook().getAuthor())
                .bookTranslator(tree.getBook().getTranslator())
                .bookPublisher(tree.getBook().getPublisher())
                .bookPublishedYear(MemoirCardDtoResponse.changeBookPublishedYear(tree.getBook().getPublishDate()))
                .build();



    }
}

