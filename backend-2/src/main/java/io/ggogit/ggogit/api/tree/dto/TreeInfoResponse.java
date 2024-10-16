package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeInfoResponse {

    private Long bookId;
    private String bookCategory;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator;
    private String bookPublisher;
    private String bookPublishedYear;
    private Integer bookTotalPage;
    private Long treeId;
    private Long memberId ;
    private Long seedId;
    private String title;
    private String description;
    private Boolean visibility;
    private String  leafCreatedAt;
    private String  createdAt;
    private Integer readingPage ;
    private String coverImageName;
    private Long treeLeafCnt;
    private Long treeLikeCnt;
    private Long treeViewCnt;

    public static TreeInfoResponse of(Tree tree, LocalDate latestLeafDate, Long leafCnt, Long likeCnt, Long viewCnt) {
        return TreeInfoResponse.builder()
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
                .leafCreatedAt(latestLeafDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .readingPage(tree.getTreeBook().getReadingPage())
                .coverImageName(tree.getBook().getImageFile())
                .treeLeafCnt(leafCnt)
                .treeLikeCnt(likeCnt)
                .treeViewCnt(viewCnt)
                .build();
    }
}
