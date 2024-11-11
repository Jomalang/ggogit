package io.ggogit.ggogit.api.leaf.dto;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeafSearchResultResponse {
    private Long treeId;
    private Long leafId;
    private String treeTitle;
    private String seedKorName;
    private String treeDescription;
    private String coverImageName;
    private String bookCategory;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator;
    private String bookPublisher;
    private String bookPublishedYear;
    private String leafTitle;
    private String leafContent;
    private String leafUpdatedAt;
    private Integer likeCount;
    private Integer viewCount;

    public static LeafSearchResultResponse ofBook(Leaf leaf) {
        return new LeafSearchResultResponse().builder()
                .treeId(leaf.getTree().getId())
                .leafId(leaf.getId())
                .treeTitle(leaf.getTree().getTitle())
                .seedKorName(leaf.getTree().getSeed().getKorName())
                .coverImageName(leaf.getTree().getBook().getImageFile())
                .bookCategory(leaf.getTree().getBook().getBookCategory().getName())
                .bookTitle(leaf.getTree().getBook().getTitle())
                .bookAuthor(leaf.getTree().getBook().getAuthor())
                .bookTranslator(leaf.getTree().getBook().getTranslator())
                .bookPublisher(leaf.getTree().getBook().getPublisher())
                .bookPublishedYear(leaf.getTree().getBook().getPublishDate().toString())
                .leafTitle(leaf.getTitle())
                .leafContent(leaf.getContent())
                .leafUpdatedAt(leaf.getUpdateTime().toString())
                .likeCount(leaf.getLikeCount())
                .viewCount(leaf.getViewCount())
                .build();
    }
    public static LeafSearchResultResponse ofEtc(Leaf leaf) {
        return new LeafSearchResultResponse().builder()
                .treeId(leaf.getTree().getId())
                .leafId(leaf.getId())
                .treeTitle(leaf.getTree().getTitle())
                .seedKorName(leaf.getTree().getSeed().getKorName())
                .coverImageName(leaf.getTree().getTreeImage() == null || leaf.getTree().getTreeImage().getName() == null ? "" : leaf.getTree().getTreeImage().getName())
                .leafTitle(leaf.getTitle())
                .leafContent(leaf.getContent())
                .leafUpdatedAt(leaf.getUpdateTime().toString())
                .likeCount(leaf.getLikeCount())
                .viewCount(leaf.getViewCount())
                .build();
    }
}
