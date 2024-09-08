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
    void findByListAll() {
        List<Tree> treeList = repo.findListAll();
        assertThat(treeList).isNotNull();
    }

    @Test
    void findByTreeId() {
        Long i = 2L;
        assertThat(repo.findByTreeId(i)).isNotNull();
    }

    @Test
    void findByMemberId() {
        Long i = 1L;
        assertThat(repo.findByMemberId(i)).isNotNull();
    }

    @Test
    void findByTitle() {
        String title = "tree";
        assertThat(repo.findByTitle(title)).isNotNull();
    }

    @Test
    void findByDescription() {
        String description = "test";
        assertThat(repo.findByDescription(description)).isNotNull();
    }
    @Test
    void findByVisibility() {
        boolean visibility = true;
        assertThat(repo.findByVisibility(visibility)).isNotNull();
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
        Tree tree = repo.findByTreeId(id);
        assertThat(tree.getTitle()).isEqualTo(title);
    }
    @Test
    void updateTreeDescription() {
        Long id = 71L;
        String description = "test description";
        repo.updateDescriptionById(id, description);
        Tree tree = repo.findByTreeId(id);
        assertThat(tree.getDescription()).isEqualTo(description);
    }
    @Test
    void updateTreeVisibility() {
        Long id = 71L;
        boolean visibility = true;
        repo.updateVisibilityById(id, visibility);
        Tree tree = repo.findByTreeId(id);
        assertThat(tree.getVisibility()).isEqualTo(visibility);
    }

    @Test
    void deleteTree() {
        Long id = 43L;
        repo.delete(id);
    }
}
