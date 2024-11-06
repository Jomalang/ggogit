package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.LeafBookCardResponse;
import io.ggogit.ggogit.api.leaf.dto.*;
import io.ggogit.ggogit.api.member.dto.MemberInfoResponse;
import io.ggogit.ggogit.domain.leaf.service.LeafDtoService;
import io.ggogit.ggogit.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<LeafItemToEndResponse> getLeafNodesToEnd(
            @PathVariable Long leafId
    ) {
        boolean isOwner = true;
        LeafItemToEndResponse responses = leafDtoService.getLeafNodeToEnd(leafId, isOwner);
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
     * 기타 리프 디테일 조회
     */
    @GetMapping("/etc/leaves/{leafId}")
    public ResponseEntity<LeafEtcDetailResponse> getEtcLeafDetail(
            @PathVariable Long leafId
    ) {
        LeafEtcDetailResponse response = leafDtoService.getLeafEtcDetail(leafId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 기타 리프 수정 상세 조회
     */
    @GetMapping("/etc/leaves/{leafId}/edit")
    public ResponseEntity<LeafEtcEditDetailResponse> getEtcLeafEditDetail(
            @PathVariable Long leafId
    ) {
        LeafEtcEditDetailResponse response = leafDtoService.getEtcLeafEditDetail(leafId);
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

    /**
     * 리프 도서 카드 리스트 조회
     */
    @GetMapping("/members/{memberId}/leaves/book/cards")
    public ResponseEntity<LeafBookCardResponse> getLeafBookCards(
            @PathVariable Long memberId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        LeafBookCardResponse responses = leafDtoService.getLeafBookCards(memberId, page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * 리프 일반 카드 리스트 조회
     */
    @GetMapping("/books/{bookId}/leaves/cards")
    public ResponseEntity<LeafCardResponse> getLeafCards(
            @PathVariable Long bookId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        LeafCardResponse responses = leafDtoService.getLeafCards(bookId, page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * 리프 씨드 타입
     */
    @GetMapping("/leaves/{leafId}/seed")
    public ResponseEntity<LeafSeedResponse> getLeafSeed(
            @PathVariable Long leafId
    ) {
        String seedType = leafDtoService.getSeedType(leafId);
        LeafSeedResponse responses = LeafSeedResponse.of(seedType);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * 리프 도서 트리의 총 도서 페이지 수
     * @param leafId
     */
    @GetMapping("/leaves/{leafId}/book/page")
    public ResponseEntity<Integer> getBookPage(
            @PathVariable Long leafId
    ) {
        int bookPage = leafDtoService.getBookPage(leafId);
        return new ResponseEntity<>(bookPage, HttpStatus.OK);
    }

    /**
     * 리프 디테일 정보
     */
    @GetMapping("/leaves/{leafId}")
    public ResponseEntity<LeafDetailResponse> getLeafDetail(
            @PathVariable Long leafId
    ) {
        LeafDetailResponse responses = leafDtoService.getLeafDetail(leafId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * 리프 회원 정보
     */
    @GetMapping("/leaves/{leafId}/member")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(
            @PathVariable Long leafId
    ) {
        MemberInfoResponse responses = leafDtoService.getMemberInfo(leafId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}