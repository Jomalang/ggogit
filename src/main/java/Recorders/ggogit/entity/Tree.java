package Recorders.ggogit.entity;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import io.micrometer.common.lang.Nullable;

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
    private BookCategoryType bookCategoryType;
    private String treeName;
    private String description;
    private Boolean visibility;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean complete;
    private int like;
    private int leaf;
    private int view;
    @Nullable
    private Integer totalPage;
    @Nullable
    private Integer readingPage;

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
        testTree.setTotalPage(400);
        testTree.setReadingPage(320);
        testTree.setBookCategoryType("nonFiction");
        testTree.setTreeName("testTreeName");
        testTree.setDescription(
                "코딩 테스트 합격자 되기 자바스크립트 편의 영감 기록을 남기는 이유는 다음과 같습니다. 영감 기록을 작성하면 책에서 배운 내용을 정리하고 이해도를 높일 수 있습니다.");
        testTree.setVisibility(true);
        testTree.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        testTree.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        testTree.setComplete(true);
        testTree.setLike(1000);
        testTree.setView(50);
        testTree.setLeaf(800);

        return testTree;

    }
}