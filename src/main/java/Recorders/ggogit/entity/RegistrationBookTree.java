package Recorders.ggogit.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationBookTree {
    private Long id;
    private TreeType treeType;
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

    public void setTreeType(String treeType) {
        this.treeType = TreeType.of(treeType);
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = BookCategoryType.of(bookCategory);
    }
}