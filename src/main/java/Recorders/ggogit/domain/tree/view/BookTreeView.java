package Recorders.ggogit.domain.tree.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookTreeView {
    private Long bookId;
    private String bookCategory;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator;
    private String bookPublisher;
    private Integer bookPublishedYear;
    private Integer bookTotalPage;
    private Boolean bookComplete;
    private Long treeId;
    private Long memberId ;
    private Long seedId;
    private String title;
    private String description;
    private Boolean visibility;
    private Date createdAt;
    private Integer readingPage ;
    private String coverImageName;
}
