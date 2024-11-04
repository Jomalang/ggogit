package io.ggogit.ggogit.domain.tree.repository;

import io.ggogit.ggogit.api.tree.dto.TreeInfoResponse;
import io.ggogit.ggogit.domain.tree.service.TreeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TreeServiceTest {

    @Autowired
    private TreeService treeService;
    
    @Test
    public void findTreeByMemberIdTest() {
        //given
        List<TreeInfoResponse> treeDtos = treeService.findTreeInfoResponseList(1L);

        // when
        for(TreeInfoResponse treeDto : treeDtos) {
            System.out.println("treeDto = " + treeDto.toString());
        }
        
        // then
     }
    
        
}
