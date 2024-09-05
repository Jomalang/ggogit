package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.repository.LeafTagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeafTagServiceImplTest {

    @Autowired
    private LeafTagServiceImpl leafTagServiceImpl;

    @MockBean
    private LeafTagRepository leafTagRepository;


    @Test
    @DisplayName("조회 테스트 | LeafTag | getLeafTagViews")
    void getLeafTagViews() {
        // given
        leafTagServiceImpl.getLeafTagViews(999L);
        // when
        // then
    }
}