package Recorders.ggogit.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tree {
    private Long id;
    private SeedCategoryType treeType;
    private String imgUrl;
    private String bookName;
    private String author;
    private String publisher;
    private Integer totalPage;
    private BookCategoryType bookCategory;
    private String treeName;
    private String description;
    private Boolean visibility;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public void setSeedCategoryType(String treeType) {
        this.treeType = SeedCategoryType.of(treeType);
    }

    public void setBookCategoryType(String bookCategory) {
        this.bookCategory = BookCategoryType.of(bookCategory);
    }
}