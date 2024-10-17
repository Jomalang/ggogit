package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.LeafItemResponse;
import io.ggogit.ggogit.domain.leaf.service.LeafService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LeafController {

    private final LeafService leafService;

    /**
     * 트리 아이디 기반으로 모든 리프 조회
     * @param treeId
     */
    @GetMapping("/trees/{treeId}/leafs")
    public void getLeafsByTreeId(
            @PathVariable("treeId") Long treeId
    ) {
        LeafItemResponse response = leafService.getLeafsByTreeId(treeId);
    }

}