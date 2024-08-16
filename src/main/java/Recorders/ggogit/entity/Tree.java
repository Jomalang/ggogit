package Recorders.ggogit.entity;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private Boolean complete;

    public void setSeedCategoryType(String seedCategory) {
        this.seedCategoryType = SeedCategoryType.of(seedCategory);
    }

    public void setBookCategoryType(String bookCategory) {
        this.bookCategoryType = BookCategoryType.of(bookCategory);
    }

    public static Tree createTestTree() {
        Tree testTree = new Tree();
        testTree.setId(1L);
        testTree.setSeedCategoryType("book");
        testTree.setImgUrl("card-felt__cover.svg");
        testTree.setBookName("testBook");
        testTree.setAuthor("testAuthor");
        testTree.setPublisher("testPub");
        testTree.setTotalPage(100);
        testTree.setBookCategoryType("nonFiction");
        testTree.setTreeName("testTreeName");
        testTree.setDescription(
                "testDes");
        testTree.setVisibility(true);
        testTree.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        testTree.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        testTree.setComplete(true);

        return testTree;

    }
}