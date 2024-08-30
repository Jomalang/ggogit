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
    void save() {
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
        Long i = 2;
        assertThat(repo.findByTreeId(i)).isNotNull();
    }

    @Test
    void findByMemberId() {
        Long i = 1;
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
    @Test
    void findMemberIdById() {
        Long i = 71;
        assertThat(repo.findMemberIdById(i)).isNotNull();
    }
    @Test
    void findTitleById() {
        Long i = 71;
        assertThat(repo.findTitleById(i)).isNotNull();
    }
    @Test
    void findDescriptionById() {
        Long i = 71;
        assertThat(repo.findDescriptionById(i)).isNotNull();
    }
    @Test
    void findVisibilityById() {
        Long i = 71;
        assertThat(repo.findVisibilityById(i)).isNotNull();
    }

    @Test
    void updateTreeTitle() {
        Long id = 71;
        String title = "test title";
        repo.updateByTitle(id, title);
        assertThat(repo.findTitleById(id)).isEqualTo(title);
    }
    @Test
    void updateTreeDescription() {
        Long id = 71;
        String description = "test description";
        repo.updateByDescription(id, description);
        assertThat(repo.findDescriptionById(id)).isEqualTo(description);
    }
    @Test
    void updateTreeVisibility() {
        Long id = 71;
        boolean visibility = true;
        repo.updateByVisibility(id, visibility);
        assertThat(repo.findVisibilityById(id)).isEqualTo(visibility);
    }

    @Test
    void deleteTree() {
        Long id = 43;
        repo.delete(id);
    }
}
