package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.Tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeSearchResultResponse {
    private Long treeId;
    private String treeTitle;
    private String treeCreatedAt;
    private String seedKorName;
    private String treeDescription;
    private String coverImageName;
    private String bookCategory;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator;
    private String bookPublisher;
    private String bookPublishedYear;

    public static TreeSearchResultResponse ofBook(Tree tree) {
        return TreeSearchResultResponse.builder()
                .treeId(tree.getId())
                .treeTitle(tree.getTitle())
                .treeCreatedAt(tree.getCreateTime().toString())
                .seedKorName(tree.getSeed().getKorName())
                .treeDescription(tree.getDescription())
                .coverImageName(tree.getBook().getImageFile())
                .bookCategory(tree.getBook().getBookCategory().getName())
                .bookTitle(tree.getBook().getTitle())
                .bookAuthor(tree.getBook().getAuthor())
                .bookTranslator(tree.getBook().getTranslator())
                .bookPublisher(tree.getBook().getPublisher())
                .bookPublishedYear(tree.getBook().getPublishDate().toString())
                .build();
    }
    public static TreeSearchResultResponse ofEtc(Tree tree) {
        return TreeSearchResultResponse.builder()
                .treeId(tree.getId())
                .treeTitle(tree.getTitle())
                .treeCreatedAt(tree.getCreateTime().toString())
                .seedKorName(tree.getSeed().getKorName())
                .treeDescription(tree.getDescription())
                .coverImageName(tree.getTreeImage() == null || tree.getTreeImage().getName() == null ? "" : tree.getTreeImage().getName())
                .build();
    }
}
