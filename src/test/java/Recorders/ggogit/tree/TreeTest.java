package Recorders.ggogit.tree;

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
    void save() {
        Tree tree;
        for (int i = 1; i < 11; ++i) {

            tree = Tree.builder()
                    .memberId(1L)
                    .seedId(1L)
                    .title("tree title" + i + "test")
                    .description("tree" + i + "description")
                    .bookMarkCount(0)
                    .visibility(true)
                    .build();
            repo.save(tree);
        }
    }

    @Test
    void getAll() {
        List<Tree> treeList = repo.getAll();
        assertThat(treeList).isNotNull();
    }

    @Test
    void getByTreeId() {
        Long i = 2L;
        assertThat(repo.getByTreeId(i)).isNotNull();
    }

    @Test
    void getByMemberId() {
        Long i = 1L;
        assertThat(repo.getByMemberId(i)).isNotNull();
    }

    @Test
    void getByTitle() {
        String title = "tree";
        assertThat(repo.getByTitle(title)).isNotNull();
    }

    @Test
    void getByDescription() {
        String description = "test";
        assertThat(repo.getByDescription(description)).isNotNull();
    }
    @Test
    void getByVisibility() {
        boolean visibility = true;
        assertThat(repo.getByVisibility(visibility)).isNotNull();
    }
//    @Test
//    void findMemberIdById() {
//        Long i = 71L;
//        assertThat(repo.findMemberIdById(i)).isNotNull();
//    }
//    @Test
//    void findTitleById() {
//        Long i = 71L;
//        assertThat(repo.findTitleById(i)).isNotNull();
//    }
//    @Test
//    void findDescriptionById() {
//        Long i = 71L;
//        assertThat(repo.findDescriptionById(i)).isNotNull();
//    }
//    @Test
//    void findVisibilityById() {
//        Long i = 71L;
//        assertThat(repo.findVisibilityById(i)).isNotNull();
//    }

    @Test
    void updateTreeTitle() {
        Long id = 71L;
        String title = "test title";
        repo.updateTitleById(id, title);
        Tree tree = repo.getByTreeId(id);
        assertThat(tree.getTitle()).isEqualTo(title);
    }
    @Test
    void updateTreeDescription() {
        Long id = 71L;
        String description = "test description";
        repo.updateDescriptionById(id, description);
        Tree tree = repo.getByTreeId(id);
        assertThat(tree.getDescription()).isEqualTo(description);
    }
    @Test
    void updateTreeVisibility() {
        Long id = 71L;
        boolean visibility = true;
        repo.updateVisibilityById(id, visibility);
        Tree tree = repo.getByTreeId(id);
        assertThat(tree.getVisibility()).isEqualTo(visibility);
    }

    @Test
    void deleteTree() {
        Long id = 43L;
        repo.delete(id);
    }
}
