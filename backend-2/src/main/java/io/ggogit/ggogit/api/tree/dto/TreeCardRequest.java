package io.ggogit.ggogit.api.tree.dto;

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
    public static TreeCardRequest toEntity(BookInfoResponse bookInfoResponse, boolean complateBook, Tree tree, Seed Seed, Long memberId){
        LocalDate publishYear = bookInfoResponse.getPublishDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return TreeCardRequest.builder()
                .bookId(bookInfoResponse.getId())
                .bookCategory(bookInfoResponse.getCategory().getName())
                .bookTitle(bookInfoResponse.getTitle())
                .bookAuthor(bookInfoResponse.getAuthor())
                .bookTranslator(bookInfoResponse.getTranslator())
                .bookPublisher(bookInfoResponse.getPublisher())
                .bookPublishedYear(publishYear.format(formatter))
                .bookComplete(complateBook)
                .coverImageName(bookInfoResponse.getImageFile())
                .treeId(tree.getId())
                .memberId(memberId)
                .seedId(Seed.getId())
                .seedKorName(Seed.getKorName())
                .title(tree.getTitle())
                .visibility(tree.getVisibility())
                .leafCreatedAt(tree.getUpdateTime())
                .build();
    }

    public static TreeCardRequest toEntity(String coverImage, Member member, Tree tree, Seed seed, String nickname){
        return TreeCardRequest.builder()
                .coverImageName(coverImage)
                .treeId(tree.getId())
                .bookAuthor(nickname)
                .memberId(member.getId())
                .seedId(seed.getId())
                .seedKorName(seed.getKorName())
                .title(tree.getTitle())
                .visibility(tree.getVisibility())
                .leafCreatedAt(tree.getUpdateTime())
                .build();
    }
}
