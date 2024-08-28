package Recorders.ggogit.domain.leaf.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@DisplayName("LeafTagRepository 테스트")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LeafTagRepositoryTest {

    @Autowired
    private LeafTagRepository leafTagRepository;

    @BeforeAll
    public static void setUp() {
    }

    @Test
    @DisplayName("저장 테스트 | LeafTag")
    void saveTest() {
        // given

        // when

        // then
    }

}