package io.ggogit.ggogit.domain.tree.mapper;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import org.springframework.stereotype.Component;

@Component
public class TreeMapper {

    public Tree toEntity(TreeTmp treeTmp, Book book, Member member) {
        return Tree.builder()
                .member(member)
                .book(book)
                .title(treeTmp.getTreeTitle())
                .description(treeTmp.getDescription())
                .visibility(treeTmp.getVisibility())
                .build();
    }
}
