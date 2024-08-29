package Recorders.ggogit.tree.memoir;

import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.repository.TreeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@Transactional
//@MybatisTest
//@Rollback

@MybatisTest
@Rollback
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TreeTest {
    @Autowired
    private TreeRepository repo;

    @Test
    void saveTree() {
        Tree tree;
        for (int i = 1; i < 11; ++i) {

            tree = Tree.builder()
                    .memberId(1)
                    .seedId(1)
                    .title("tree title" + i + "test")
                    .description("tree" + i + "description")
                    .bookMarkCountNum(0)
                    .visibility(true)
                    .build();
            repo.saveTree(tree);
        }
    }

    @Test
    void findTreeListAll(){
        List<Tree> treeList = repo.findTreeListAll();
        assertThat(treeList).isNotNull();
    }
    @Test
    void findTreeByTreeId() {
        long i = 2;
        assertThat(repo.findTreeByTreeId(i)).isNotNull();
    }
    @Test
    void findTreeByMemberId() {
        long i = 1;
        assertThat(repo.findTreeByMemberId(i)).isNotNull();
    }
    @Test
    void findTreeByTitle() {
        String title = "tree";
        assertThat(repo.findTreeByTitle(title)).isNotNull();
    }
    @Test
    void findTreeByDescription() {
        String description = "test";
        assertThat(repo.findTreeByDescription(description)).isNotNull();
    }
    @Test
    void findTreeByVisibility() {
        boolean visibility = true;
        assertThat(repo.findTreeByVisibility(visibility)).isNotNull();
    }

    @Test
    void updateTreeTitle() {
        long id = 2;
        String title = "test title";
        repo.updateTreeTitle(id, title);
        assertThat(repo.findTreeByTitle(title)).isEqualTo(title);
    }
    @Test
    void updateTreeDescription() {
        long id = 2;
        String description = "test description";
        repo.updateTreeDescription(id, description);
        assertThat(repo.findTreeByDescription(description)).isEqualTo(description);
    }
    @Test
    void updateTreeVisibility() {
        long id = 2;
        boolean visibility = true;
        repo.updateTreeVisibility(id, visibility);
        assertThat(repo.findTreeByVisibility(visibility)).isEqualTo(visibility);
    }

    @Test
    void deleteTree() {
        long id = 43;
        repo.deleteTree(id);
    }
}
