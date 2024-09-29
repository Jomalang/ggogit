package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.view.LeafItemView;
import Recorders.ggogit.domain.leaf.view.LeafListBranchView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LeafServiceImplTest {

    @Autowired
    private LeafServiceImpl leafServiceImpl;

    @Test
    void getBreadcrumb() {
        Long treeId = 1L;
        Long leafId = 99932L;
        LeafListBranchView leafRecentSaveBranchView = leafServiceImpl.getBranchInfo(treeId, leafId);
        System.out.println(leafRecentSaveBranchView);
    }

    @Test
    void getLeafItems() {
        Long treeId = 1L;
        Long leafId = 2L;
        List<LeafItemView> leafItems = leafServiceImpl.getLeafItems(treeId, leafId);
    }
}