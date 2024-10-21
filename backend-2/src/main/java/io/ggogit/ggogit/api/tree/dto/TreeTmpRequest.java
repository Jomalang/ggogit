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
    private Integer totalPage;

    private Long seedId;

    private String treeTitle;
    private String description;
    private String imageFile;
    private Boolean visibility;
    private Date createTime;


    public TreeTmp toTreeTmp(Member member) {
        return TreeTmp.builder()
                .id(this.id)
                .member(member)
                .bookTitle(this.bookTitle)
                .author(this.author)
                .publisher(this.publisher)
                .totalPage(this.totalPage)
                .treeTitle(this.treeTitle)
                .description(this.description)
                .imageFile(this.imageFile)
                .visibility(this.visibility)
                .build();

    }
}

