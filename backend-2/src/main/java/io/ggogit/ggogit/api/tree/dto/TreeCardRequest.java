package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.api.book.dto.BookCategoryResponse;
import io.ggogit.ggogit.api.book.dto.BookInfoResponse;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeCardRequest {
    private Long bookId;
    private String bookCategory;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator;
    private String bookPublisher;
    private String bookPublishedYear;
    private Boolean bookComplete;
    private String coverImageName;
    private Long treeId;
    private Long memberId ;
    private Long seedId;
    private String seedKorName;
    private String title;
    private Boolean visibility;
    private LocalDateTime leafCreatedAt;

    public TreeCardRequest of(){
        return TreeCardRequest.builder()
                .bookId(this.bookId)
                .bookCategory(this.bookCategory)
                .bookTitle(this.bookTitle)
                .bookAuthor(this.bookAuthor)
                .bookTranslator(this.bookTranslator)
                .bookPublisher(this.bookPublisher)
                .bookPublishedYear(this.bookPublishedYear)
                .bookComplete(this.bookComplete)
                .coverImageName(this.coverImageName)
                .treeId(this.treeId)
                .memberId(this.memberId)
                .seedId(this.seedId)
                .seedKorName(this.seedKorName)
                .title(this.title)
                .visibility(this.visibility)
                .leafCreatedAt(this.leafCreatedAt)
                .build();
    }
}
