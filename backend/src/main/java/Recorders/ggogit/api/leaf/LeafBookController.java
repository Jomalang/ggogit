package Recorders.ggogit.api.leaf;

import Recorders.ggogit.api.leaf.dto.request.LeafBookCreateRequest;
import Recorders.ggogit.api.leaf.dto.request.LeafBookFirstCreateRequest;
import Recorders.ggogit.api.leaf.dto.request.LeafBookUpdateRequest;
import Recorders.ggogit.api.leaf.dto.response.LeafBookCreateResponse;
import Recorders.ggogit.api.leaf.dto.response.LeafBookFirstCreateResponse;
import Recorders.ggogit.api.leaf.dto.response.LeafBookUpdateResponse;
import Recorders.ggogit.domain.leaf.service.LeafBookService;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController("apiLeafBookController")
public class LeafBookController {

    private final LeafBookService leafBookService;

    /**
     * 도서 리프 생성 (첫 리프)
     * @param request: 생성 요청
     * @return
     */
    @PostMapping("/leafs/book/first")
    public ResponseEntity<LeafBookFirstCreateResponse> createLeafBookFirst(
            @Valid @RequestBody LeafBookFirstCreateRequest request
    ) {
        request.isValidation(); // validation check

        Long memberId = 1L;
        LeafBookView requestLeafBookView = request.toLeafBookView();

        LeafBookView leafBookView = leafBookService.register(requestLeafBookView, memberId);
        return ResponseEntity.ok(LeafBookFirstCreateResponse.of(leafBookView));
    }

    /**
     * 도서 리프 생성 (부모 리프)
     * @param request: 생성 요청
     * @return
     */
    @PostMapping("/leafs/book/parent/{leafId}")
    public ResponseEntity<LeafBookCreateResponse> createLeafBookParent(
            @Valid @RequestBody LeafBookCreateRequest request,
            @PathVariable Long leafId
    ) {
        request.isValidation(); // validation check
        Long memberId = 1L;

        if (!leafBookService.isOwner(leafId, memberId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        LeafBookView requestLeafBookView = request.toLeafBookView();

        LeafBookView leafBookView = leafBookService.register(requestLeafBookView, memberId);
        return ResponseEntity.ok(LeafBookCreateResponse.of(leafBookView));
    }

    /**
     * 도서 리프 수정
     * @param leafId: 리프 아이디
     * @param request: 수정 요청
     * @return
     */
    @PutMapping("/leafs/book/{leafId}")
    public ResponseEntity<LeafBookUpdateResponse> updateLeafBook(
            @PathVariable Long leafId,
            @Valid @RequestBody LeafBookUpdateRequest request
    ) {
        request.isValidation(); // validation check

        Long memberId = 1L;

        if (!leafBookService.isOwner(leafId, memberId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        LeafBookView requestLeafBookView = request.toLeafBookView();

        LeafBookView leafBookView = leafBookService.update(leafId, requestLeafBookView, memberId);
        return ResponseEntity.ok(LeafBookUpdateResponse.of(leafBookView));
    }

    /**
     * 도서 리프 삭제
     * @param leafId: 리프 아이디
     * @return
     */
    @DeleteMapping("/leafs/book/{leafId}")
    public ResponseEntity<String> deleteLeafBook(
            @PathVariable Long leafId
    ) {
        Long memberId = 1L;

        if (!leafBookService.isOwner(leafId, memberId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        leafBookService.remove(leafId);
        return ResponseEntity.ok("success");
    }
}