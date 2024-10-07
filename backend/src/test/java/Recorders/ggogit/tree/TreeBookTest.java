package Recorders.ggogit.tree;

import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.entity.TreeBook;
import Recorders.ggogit.domain.tree.repository.TreeBookRepository;
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
public class TreeBookTest {
    @Autowired
    private TreeBookRepository repo;

    @Test
    void save() {
        TreeBook treeBook;
            treeBook = TreeBook.builder()
                    .treeId(2L)
                    .readingPage(320)
                    .build();
            repo.save(treeBook);
    }


    @Test
    void findById() {
        TreeBook tb = repo.findById(71L);
        assertThat(tb).isNotNull();
    }


    @Test
    void update() {
        TreeBook treeBook;
        treeBook = TreeBook.builder()
                .treeId(71L)
                .readingPage(30)
                .build();
        repo.update(treeBook);
        TreeBook test = repo.findById(71L);
        assertThat(test.getReadingPage()).isEqualTo(treeBook.getReadingPage());
    }


    @Test
    void delete() {
        repo.delete(71L);
    assertThat(repo.findById(71L)).isNull();
    }
}
