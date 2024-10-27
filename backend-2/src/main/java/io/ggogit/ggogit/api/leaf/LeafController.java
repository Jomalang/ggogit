package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.*;
import io.ggogit.ggogit.domain.leaf.service.LeafDtoService;
import io.ggogit.ggogit.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LeafController {

    private final LeafDtoService leafDtoService;
    private final MemberService memberService;

    /**
     * 리프 리스트 화면 하단 브랜치 정보 조회
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
        LeafBookDetailResponse response = leafDtoService.getBookDetail(leafId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 도서 리프 수정 상세 조회
     */
    @GetMapping("/book/leaves/{leafId}/edit")
    public ResponseEntity<LeafBookEditDetailResponse> getLeafBookEditDetail(
            @PathVariable Long leafId
    ) {
        LeafBookEditDetailResponse response = leafDtoService.getLeafBookEditDetail(leafId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 리프의 브레드크럼 조회
     */
    @GetMapping("/leaves/{leafId}/breadcrumb")
    public ResponseEntity<LeafBreadcrumbResponse> getLeafBreadcrumb(
            @PathVariable Long leafId
    ) {
        LeafBreadcrumbResponse responses = leafDtoService.getLeafBreadcrumb(leafId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * 리프 생성 및 수정시 이전 정보 조회
     */
    @GetMapping("leaves/{leafId}/before")
    public ResponseEntity<LeafBeforeNodeInfoResponse> getLeafBefore(
            @PathVariable Long leafId
    ) {
        LeafBeforeNodeInfoResponse responses = leafDtoService.getLeafBeforeNodeInfo(leafId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}