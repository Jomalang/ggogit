package Recorders.ggogit.domain.tree.entity;

import Recorders.ggogit.domain.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeSaveTmp {
    private Long id;
    private Long memberId;
    private Long bookCategoryId;
    private String bookTitle;
    private String author;
    private String publisher;
    private Long totalPage;
    private Long bookId;
    private Long seedId;
    private String treeTitle;
    private String description;
    private String imageFile;
    private Boolean visibility;
    private LocalDateTime createTime;

    public Book toBook() {
        return Book.builder()
                .id(bookId)
                .memberId(memberId)
                .bookCategoryId(bookCategoryId)
                .totalPage(totalPage)
                .title(bookTitle)
                .author(author)
                .publisher(publisher)
                .imageFile(imageFile)
                .resourceFrom(false)
                .publicDate(LocalDateTime.now())
                .build();
    }

    public Tree toTree() {
        return Tree.builder()
                .id(id)
                .memberId(memberId)
                .seedId(seedId)
                .title(treeTitle)
                .description(description)
                .bookMarkCount(0)
                .visibility(visibility)
                .build();
    }

    public TreeBook toTreeBook() {
        return TreeBook.builder()
                .readingPage(0)
                .build();
    }

    public TreeImage toTreeImage() {
        return TreeImage.builder()
                .name(imageFile)
                .build();
    }
}