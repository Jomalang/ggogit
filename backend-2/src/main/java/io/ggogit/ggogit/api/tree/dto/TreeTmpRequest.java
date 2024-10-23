package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeTmpRequest {

    // 도서 정보 (Book Tree인 경우 사용)
    private Long bookId;
    private Long bookCategoryId;
    private String bookTitle;
    private String author;
    private String publisher;
    private Integer totalPage;

    // 씨앗 정보 (ETC tree인 경우 사용)
    private Long seedId;

    // 트리 정보
    private String treeTitle;
    private String description;
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

