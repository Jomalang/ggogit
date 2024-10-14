package io.ggogit.ggogit.domain.tree.mapper;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeSaveTmp;
import org.springframework.stereotype.Component;

@Component
public class TreeMapper {

    public Tree toEntity(TreeSaveTmp treeSaveTmp, Book book, Member member) {
        return Tree.builder()
                .member(member)
                .book(book)
                .title(treeSaveTmp.getTreeTitle())
                .description(treeSaveTmp.getDescription())
                .visibility(treeSaveTmp.getVisibility())
                .build();
    }
}
