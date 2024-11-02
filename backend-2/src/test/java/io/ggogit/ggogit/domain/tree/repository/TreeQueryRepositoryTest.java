package io.ggogit.ggogit.domain.tree.repository;

import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.query.TreeQueryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.util.List;

@SpringBootTest
public class TreeQueryRepositoryTest {

    @Autowired
    private TreeQueryRepository treeQueryRepositoryImpl;

        @Test
        @Description("queryDsl을 이용한 fetchJoin 테스트")
        public void findTreeByMemberIdFetchTest() {
            //given
            List<Tree> treeByMemberIdFetch = treeQueryRepositoryImpl.findTreeByMemberIdFetch(2L);

            // when
            for (Tree tree : treeByMemberIdFetch) {
                System.out.println("tree = " + tree.toString());
                for(int i = 0; i < tree.getLeaf().size(); i++) {
                    System.out.println("tree.getLeaf().get(i) = " + tree.getLeaf().get(i).toString());
                }
            }
            // then

}

}

