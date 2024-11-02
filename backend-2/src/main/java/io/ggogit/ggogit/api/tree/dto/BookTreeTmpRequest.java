package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookTreeTmpRequest {

    // 도서 정보 (Book Tree인 경우 사용)
    @NotNull(message = "도서 정보를 입력해 주세요.")
    private Long bookId;
    @NotBlank(message = "도서 제목을 입력해 주세요.")
    private String bookTitle;
    @NotBlank(message = "저자를 입력해 주세요.")
    private String author;
    @NotBlank(message = "출판사를 입력해 주세요.")
    private String publisher;
    @NotBlank(message = "출판일을 입력해 주세요.")
    private String publishDate;
    @NotNull(message = "총 페이지 수를 입력해 주세요.")
    private Integer totalPage;

    // 도서 카테고리 정보
    @NotNull(message = "도서 카테고리를 입력해 주세요.")
    private Long bookCategoryId;

    // 씨앗 정보 (ETC tree인 경우 사용)
    @NotNull(message = "씨앗 정보를 입력해 주세요.")
    private Long seedId;

    // 트리 정보
    @NotBlank(message = "트리 제목을 입력해 주세요.")
    private String treeTitle;
    @NotBlank(message = "트리 설명을 입력해 주세요.")
    private String description;
    @NotNull(message = "공개여부를 설정해주세요.")
    private Boolean visibility;

    private Date createTime;

    // 이미지 파일
    private String imageFile;

    public TreeTmp toTreeTmp() {
        return TreeTmp.builder()
                .member(null)
                .bookTitle(bookTitle)
                .author(author)
                .publisher(publisher)
                .totalPage(totalPage)
                .treeTitle(treeTitle)
                .description(description)
                .imageFile(imageFile)
                .visibility(visibility)
                .build();
    }
}

