package Recorders.ggogit.domain.leaf.repository;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

@MybatisTest
@Rollback
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LeafTagRepositoryTest {

    @Autowired
    private LeafTagRepository leafTagRepository;

    @BeforeAll
    static void beforeAll() {}

    @Test
    @DisplayName("저장 테스트 | LeafTag")
    void saveTest() {
        // given
        LeafTag leafTag = LeafTag.builder()
                .id(1L)
                .memberId(999L)
                .name("TagTest")
                .updateTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .build();

        // when
        leafTagRepository.save(leafTag);
    }
}