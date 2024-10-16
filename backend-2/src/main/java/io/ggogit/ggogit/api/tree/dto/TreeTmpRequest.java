package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Seed;
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

    public static TreeTmp toEtcTreeTmp(TreeTmpRequest request, Member member, Seed seed) {
        return TreeTmp.builder()
                .member(member)
                .seed(seed)
                .treeTitle(request.getTreeTitle())
                .description(request.getDescription())
                .imageFile(request.getImageFile())
                .visibility(request.getVisibility())
                .build();

    }
    public static TreeTmp toBookTreeTmp(TreeTmpRequest request, Member member, BookCategory bookCategory, Seed seed) {
        return TreeTmp.builder()
                .id(request.getId())
                .member(member)
                .bookCategory(bookCategory)
                .bookTitle(request.getBookTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .totalPage(request.getTotalPage())
                .seed(seed)
                .treeTitle(request.getTreeTitle())
                .description(request.getDescription())
                .imageFile(request.getImageFile())
                .visibility(request.getVisibility())
                .build();

    }
}

