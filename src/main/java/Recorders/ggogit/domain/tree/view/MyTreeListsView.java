package Recorders.ggogit.domain.tree.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Repository
public class MyTreeListsView {
    private Long bookId;
    private String bookCategory;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator;
    private String bookPublisher;
    private Integer bookPublishedYear;
    private Integer bookTotalPage;
    private Long treeId;
    private Long seedId;
    private String title;
    private Boolean visibility;
    private Date leafCreatedAt;
    private Integer readingPage ;
    private String coverImageName;
    private Long treeLeafCnt;
    private Long treeLikeCnt;
    private Long treeViewCnt;
}
