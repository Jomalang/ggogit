package io.ggogit.ggogit.domain.book.mapper;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.TreeSaveTmp;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookMapper {

    public Book toEntity(TreeSaveTmp treeSaveTmp, Member member) {
        return Book.builder()
                .member(member)
                .bookCategory(treeSaveTmp.getBookCategory())
                .totalPage(treeSaveTmp.getTotalPage())
                .title(treeSaveTmp.getBookTitle())
                .author(treeSaveTmp.getAuthor())
                .publisher(treeSaveTmp.getPublisher())
                .imageFile(treeSaveTmp.getImageFile())
                .publishDate(LocalDate.now())
                .build();
    }
}