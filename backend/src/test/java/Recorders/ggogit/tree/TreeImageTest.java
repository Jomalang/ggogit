package Recorders.ggogit.tree;

import Recorders.ggogit.domain.tree.entity.TreeImage;
import Recorders.ggogit.domain.tree.repository.TreeImageRepository;
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
public class TreeImageTest {
    @Autowired
    private TreeImageRepository repo;

    @Test
    void save() {
        TreeImage treeImage;
            treeImage = TreeImage.builder()
                    .treeId(2L)
                    .name("ddd.jpg")
                    .build();
            repo.save(treeImage);
            assertThat(repo.findById(2L)).isEqualTo(treeImage);
    }


    @Test
    void findById() {
        TreeImage treeImage;
        treeImage = TreeImage.builder()
                .treeId(71L)
                .name("aaa.jpg")
                .build();
        assertThat(repo.findById(71L)).isEqualTo(treeImage);
    }


    @Test
    void update() {
        TreeImage treeImage;
        treeImage = TreeImage.builder()
                .treeId(71L)
                .name("nnn.jpg")
                .build();

        repo.update(treeImage);
        TreeImage test = repo.findById(71L);
        assertThat(test.getName()).isEqualTo(treeImage.getName());
    }


    @Test
    void delete() {
        repo.delete(71L);
    assertThat(repo.findById(71L)).isNull();
    }
}
