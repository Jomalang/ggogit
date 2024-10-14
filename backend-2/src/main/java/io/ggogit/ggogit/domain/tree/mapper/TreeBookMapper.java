package io.ggogit.ggogit.domain.tree.mapper;

import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeBook;
import org.springframework.stereotype.Component;

@Component
public class TreeBookMapper {
    public TreeBook toEntity(Tree tree) {
        return TreeBook.builder()
                .tree(tree)
                .readingPage(0L)
                .build();
    }
}
