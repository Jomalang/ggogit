package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.repository.LeafTagRepository;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeafTagServiceImplTest {

    @Autowired
    private LeafTagServiceImpl leafTagServiceImpl;

    @MockBean
    private LeafTagRepository leafTagRepository;

    @Test
    @DisplayName("조회 테스트 | LeafTag | getLeafTag")
    void getLeafTag() {
        // given
        Long leafTagId = 1L;

        // when
        leafTagServiceImpl.getLeafTag(leafTagId);

        // then
        //assertThat(leafTag).isNotNull();
    }

    @Test
    @DisplayName("조회 테스트 | LeafTag | getLeafTags")
    void getLeafTags() {
        // given
        Long memberId = 999L;
        String search = "test";
        Long page = 1L;
        Long size = 10L;

        // when
        List<LeafTag> leafTags = leafTagServiceImpl.getLeafTags(memberId, search, page, size);
        System.out.println(leafTags);

        // then
        //assertThat(leafTags).isNotNull();
    }
}