package Recorders.ggogit.tree;

import Recorders.ggogit.domain.tree.entity.TreeLike;
import Recorders.ggogit.domain.tree.repository.TreeLikeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

//@Transactional
//@MybatisTest
//@Rollback

@MybatisTest
@Rollback
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TreeLikeTest {
    @Autowired
    private TreeLikeRepository repo;

    @Test
    void save() {
        TreeLike treeLike;
            treeLike = TreeLike.builder()
                    .memberId(1L)
                    .treeId(2L)
                    .activate(true)
                    .build();
            repo.save(treeLike);
            TreeLike test = repo.findById(1L,2L);
            assertThat(test.getMemberId()).isEqualTo(treeLike.getMemberId());
            assertThat(test.getTreeId()).isEqualTo(treeLike.getTreeId());
            assertThat(test.isActivate()).isEqualTo(treeLike.isActivate());
    }


    @Test
    void findById() {
        TreeLike treeLike;
        treeLike = TreeLike.builder()
                .memberId(1)
                .treeId(71L)
                .activate(false)
                .build();
        TreeLike test = repo.findById(1L,71L);
        assertThat(test.getMemberId()).isEqualTo(treeLike.getMemberId());
        assertThat(test.getTreeId()).isEqualTo(treeLike.getTreeId());
        assertThat(test.isActivate()).isEqualTo(treeLike.isActivate());
    }


    @Test
    void update() {
        TreeLike treeLike;
        treeLike = TreeLike.builder()
                .memberId(1)
                .treeId(71L)
                .activate(true)
                .build();

        repo.update(treeLike);
        TreeLike test = repo.findById(1L,71L);
        assertThat(test.isActivate()).isEqualTo(treeLike.isActivate());
    }


    @Test
    void delete() {
        repo.delete(1L, 71L);
    assertThat(repo.findById(1L,71L)).isNull();
    }
}
