package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchInfoResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafItemResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.assertj.core.api.Assertions.assertThat;

@Profile("test")
@SpringBootTest
class LeafDtoServiceImplTest {

    @Autowired
    private LeafDtoService leafDtoService;

    @Test
    @DisplayName("리프 노드의 Root 부터 End 까지 조회")
    void getLeafNodeRootToEnd() {
        // given
        Long leafId = 47L;
        boolean isOwner = true;

        // when
        LeafItemResponse leafItemResponse = leafDtoService.getLeafNodeRootToEnd(leafId, isOwner);

        // then
        assertThat(leafItemResponse.getItems()).isNotEmpty();
    }

    @Test
    @DisplayName("리프 ID의 브랜치 정보 조회")
    void getBranchInfo() {
        // given
        Long leafId = 47L;

        // when
        LeafBranchInfoResponse leafBranchInfoResponse = leafDtoService.getBranchInfo(leafId);

        // then
        System.out.println(leafBranchInfoResponse);
    }

    @Test
    @DisplayName("리프 노드의 End 까지 조회")
    void getLeafNodeToEnd() {
        // given
        Long leafId = 31L;

        // when
        LeafItemResponse leafItemResponse = leafDtoService.getLeafNodeToEnd(leafId, true);

        // then
        System.out.println(leafItemResponse);
    }
}