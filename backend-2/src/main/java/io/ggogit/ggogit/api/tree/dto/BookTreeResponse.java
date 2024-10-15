package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookTreeResponse {

    private Long bookId;        //nullable 일수도 있을것같음
    private String bookCategory; //Book entity와 자료형 다름
    private Integer bookTotalPage;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator; //Book entity에 없음
    private String bookPublisher;
    private String bookPublishedYear;
    private Boolean bookComplete;
    private Long treeId;
    private Long memberId;
    private Long seedId;
    private String title;
    private String description;
    private Boolean visibility;
    private String createdAt;
    private Long readingPage;
    private String coverImageName;

    public static BookTreeResponse of(Tree tree) {
        return BookTreeResponse.builder()
                .bookId(tree.getBook().getId())
                .bookCategory(tree.getBook().getBookCategory().getName())
                .bookTitle(tree.getBook().getTitle())
                .bookAuthor(tree.getBook().getAuthor())
                .bookTranslator(tree.getBook().getTranslator())
                .bookPublisher(tree.getBook().getPublisher())
                .bookPublishedYear(tree.getBook().getPublishDate().format(DateTimeFormatter.ofPattern("yyyy")))
                .bookTotalPage(tree.getBook().getTotalPage())
                .treeId(tree.getId())
                .memberId(tree.getMember().getId())
                .seedId(tree.getSeed().getId())
                .title(tree.getTitle())
                .description(tree.getDescription())
                .visibility(tree.getVisibility())
                .createdAt(tree.getLeaf().getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .readingPage(tree.getTreeBook().getReadingPage())
                .coverImageName(tree.getBook().getImageFile())
                .build();
    }
}