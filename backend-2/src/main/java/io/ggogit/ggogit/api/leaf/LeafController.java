package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchInfoResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafDetailResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafItemResponse;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.service.LeafDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leaves")
@RequiredArgsConstructor
public class LeafController {

    private final LeafDtoService leafDtoService;

    /**
     * 브랜치 정보 조회
     */
    @GetMapping("/{leafId}/branch")
    public ResponseEntity<LeafBranchInfoResponse> getLeafBranch(
            @PathVariable Long leafId
    ) {
        LeafBranchInfoResponse responses = leafDtoService.getBranchInfo(leafId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * 리프 노드의 Root 부터 End 까지 조회
     */
    @GetMapping("/{leafId}/all")
    public ResponseEntity<LeafItemResponse> getLeafNodesRootToEnd(
            @PathVariable Long leafId
    ) {
        boolean isOwner = true;
            LeafItemResponse responses = leafDtoService.getLeafNodeRootToEnd(leafId, isOwner);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * 리프 노드의 End 까지 조회
     */
    @GetMapping("/{leafId}/end")
    public ResponseEntity<LeafItemResponse> getLeafNodesToEnd(
            @PathVariable Long leafId
    ) {
        boolean isOwner = true;
        LeafItemResponse responses = leafDtoService.getLeafNodeToEnd(leafId, isOwner);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{leafId}")
    public ResponseEntity<LeafDetailResponse> detailLeaf(
            @PathVariable Long leafId
    ) {
        Leaf leaf = leafDtoService.findById(leafId);
        LeafDetailResponse response = LeafDetailResponse.of(leaf);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}