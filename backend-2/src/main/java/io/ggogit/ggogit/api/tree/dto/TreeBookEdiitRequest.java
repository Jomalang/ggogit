package io.ggogit.ggogit.api.tree.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeBookEdiitRequest {
    private Boolean isAuto;
    private String author;
    private Long bookCategoryId;
    private String bookCategoryName;
    private String title;
    private LocalDate publishDate;
    private String publisher;
    private Integer totalPage;
    private Long seedId;
    private String image;
    private String treeTitle;
    private String description;
    private Long treeId;
    private Boolean visibility;
}
