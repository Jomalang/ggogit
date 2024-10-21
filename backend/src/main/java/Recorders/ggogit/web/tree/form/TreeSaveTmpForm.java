package Recorders.ggogit.web.tree.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class TreeSaveTmpForm {
    private Long id;
    private Long memberId;
    private Long bookId;
    private Long bookCategoryId;
    private String bookTitle;
    private String author;
    private String publisher;
    private Long totalPage;

    private Long seedId;

    private String treeTitle;
    private String description;
    private String imageFile;
    private Boolean visibility;
    private Date createTime;


}
