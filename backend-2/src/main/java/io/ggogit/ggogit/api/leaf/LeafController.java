package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchInfoResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafBookDetailResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafItemResponse;
import io.ggogit.ggogit.domain.leaf.service.LeafDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LeafController {

    private final LeafDtoService leafDtoService;

    /**
     * 브랜치 정보 조회
     */
    @GetMapping("/leaves/{leafId}/branch")
    public ResponseEntity<LeafBranchInfoResponse> getLeafBranch(
            @PathVariable Long leafId
    ) {
        LeafBranchInfoResponse responses = leafDtoService.getBranchInfo(leafId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * 리프 리스트 노드의 Root 부터 End 까지 조회
     */
    @GetMapping("/leaves/{leafId}/all")
    public ResponseEntity<LeafItemResponse> getLeafNodesRootToEnd(
            @PathVariable Long leafId
    ) {
        boolean isOwner = true;
            LeafItemResponse responses = leafDtoService.getLeafNodeRootToEnd(leafId, isOwner);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * 리프 리스트 노드의 End 까지 조회
     */
    @GetMapping("/leaves/{leafId}/end")
    public ResponseEntity<LeafItemResponse> getLeafNodesToEnd(
            @PathVariable Long leafId
    ) {
        boolean isOwner = true;
        LeafItemResponse responses = leafDtoService.getLeafNodeToEnd(leafId, isOwner);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * 도서 리프 상세 조회
     */
    @GetMapping("/book/leaves/{leafId}")
    public ResponseEntity<LeafBookDetailResponse> getLeafBookDetail(
            @PathVariable Long leafId
    ) {
        LeafBookDetailResponse response = leafDtoService.findById(leafId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}