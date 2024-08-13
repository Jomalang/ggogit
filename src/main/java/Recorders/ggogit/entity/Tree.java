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
    private SeedCategoryType seedCategoryType;
    private String imgUrl;
    private String bookName;
    private String author;
    private String publisher;
    private Integer totalPage;
    private BookCategoryType bookCategoryType;
    private String treeName;
    private String description;
    private Boolean visibility;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public void setSeedCategoryType(String seedCategory) {
        this.seedCategoryType = SeedCategoryType.of(seedCategory);
    }

    public void setBookCategoryType(String bookCategory) {
        this.bookCategoryType = BookCategoryType.of(bookCategory);
    }
}