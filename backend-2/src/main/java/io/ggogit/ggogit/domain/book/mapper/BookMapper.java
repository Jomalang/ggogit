package io.ggogit.ggogit.domain.book.mapper;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookMapper {

    public Book toEntity(TreeTmp treeTmp, Member member) {
        return Book.builder()
                .member(member)
                .bookCategory(treeTmp.getBookCategory())
                .totalPage(treeTmp.getTotalPage())
                .title(treeTmp.getBookTitle())
                .author(treeTmp.getAuthor())
                .publisher(treeTmp.getPublisher())
                .imageFile(treeTmp.getImageFile())
                .publishDate(LocalDate.now())
                .build();
    }
}