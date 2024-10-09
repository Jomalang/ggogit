
package Recorders.ggogit.api.leaf;

import Recorders.ggogit.api.leaf.dto.request.LeafEtcCreateRequest;
import Recorders.ggogit.api.leaf.dto.request.LeafEtcFirstCreateRequest;
import Recorders.ggogit.api.leaf.dto.request.LeafEtcUpdateRequest;
import Recorders.ggogit.api.leaf.dto.response.LeafEtcCreateResponse;
import Recorders.ggogit.api.leaf.dto.response.LeafEtcFirstCreateResponse;
import Recorders.ggogit.api.leaf.dto.response.LeafEtcUpdateResponse;
import Recorders.ggogit.domain.leaf.service.LeafEtcService;
import Recorders.ggogit.domain.leaf.view.LeafEtcView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController("apiLeafEtcController")
public class LeafEtcController {

    private final LeafEtcService leafEtcService;

    /**
     * etc 리프 생성 (첫 리프)
     * @param request: 생성 요청
     * @return LeafEtcFirstCreateResponse
     */
    @PostMapping("/leafs/first")
    public ResponseEntity<LeafEtcFirstCreateResponse> createLeafEtcFirst(
            @Valid @RequestBody LeafEtcFirstCreateRequest request
    ) {
        request.isValidation(); // validation check

        Long memberId = 1L;
        LeafEtcView requestLeafEtcView = request.toLeafEtcView();

        LeafEtcView leafEtcView = leafEtcService.register(requestLeafEtcView, memberId);
        return ResponseEntity.ok(LeafEtcFirstCreateResponse.of(leafEtcView));
    }

    /**
     * etc 리프 생성 (부모 리프)
     * @param request: 생성 요청
     * @return LeafEtcCreateResponse
     */
    @PostMapping("/leafs/parent/{leafId}")
    public ResponseEntity<LeafEtcCreateResponse> createLeafEtcParent(
            @PathVariable Long leafId,
            @Valid @RequestBody LeafEtcCreateRequest request
    ) {
        request.isValidation(); // validation check

        Long memberId = 1L;

        if (leafEtcService.isOwner(leafId, memberId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        LeafEtcView requestLeafEtcView = request.toLeafEtcView();

        LeafEtcView leafEtcView = leafEtcService.register(requestLeafEtcView, memberId);
        return ResponseEntity.ok(LeafEtcCreateResponse.of(leafEtcView));
    }

    /**
     * etc 리프 수정
     * @param request: 수정 요청
     * @return LeafEtcCreateResponse
     */
    @PutMapping("/leafs/{leafId}")
    public ResponseEntity<LeafEtcUpdateResponse> updateLeafEtc(
            @PathVariable Long leafId,
            @Valid @RequestBody LeafEtcUpdateRequest request
    ) {
        request.isValidation(); // validation check

        Long memberId = 1L;

        if (leafEtcService.isOwner(leafId, memberId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        LeafEtcView requestLeafEtcView = request.toLeafEtcView();
        LeafEtcView leafEtcView = leafEtcService.update(leafId, requestLeafEtcView, memberId);
        return ResponseEntity.ok(LeafEtcUpdateResponse.of(leafEtcView));
    }

    /**
     * etc 리프 삭제
     * @param leafId: 리프 아이디
     * @return
     */
    @DeleteMapping("/leafs/{leafId}")
    public ResponseEntity<Void> deleteLeafEtc(
            @PathVariable Long leafId
    ) {
        Long memberId = 1L;

        if (leafEtcService.isOwner(leafId, memberId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        leafEtcService.remove(leafId);
        return ResponseEntity.ok().build();
    }
}