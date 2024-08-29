package Recorders.ggogit.domain.leaf.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;


@MybatisTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용
@Sql("/domain/leaf/repository/leaf-tag-map-repository-test.sql")
class LeafTagMapRepositoryTest {

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}