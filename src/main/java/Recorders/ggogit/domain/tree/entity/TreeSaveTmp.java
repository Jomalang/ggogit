package Recorders.ggogit.domain.tree.entity;

import Recorders.ggogit.domain.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
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
//                .totalPage(totalPage) // db 에 int 로 되어있음 long -> int
                .title(bookTitle)
                .author(author)
                .publisher(publisher)
                .imageFile(imageFile)
                .publishDate(Date.valueOf(LocalDate.now()))
                .build();
    }

    public Tree toTree() {
        return Tree.builder()
                .id(id)
                .memberId(memberId)
                .bookId(bookId)
                .seedId(seedId)
                .title(treeTitle)
                .description(description)
                .bookMarkCount(0)
                .visibility(visibility)
                .build();
    }

    public TreeImage toTreeImage() {
        return TreeImage.builder()
                .name(imageFile)
                .build();
    }

    public boolean hasImage() {
        return imageFile != null && !imageFile.isEmpty();
    }

    public String getFilePath() {
        return imageFile;
    }
}