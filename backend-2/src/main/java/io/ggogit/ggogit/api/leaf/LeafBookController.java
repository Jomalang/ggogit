package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.BookLeafEditResponse;
import io.ggogit.ggogit.api.leaf.dto.BookLeafRequest;
import io.ggogit.ggogit.api.leaf.dto.BookLeafResponse;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import io.ggogit.ggogit.domain.leaf.service.LeafBookService;
import io.ggogit.ggogit.domain.member.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LeafBookController {

    private final LeafBookService leafBookService;

    @PostMapping("/book/first/leaves")
    public ResponseEntity<BookLeafResponse> createFirstBookLeaf(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @Valid @RequestBody BookLeafRequest dto
    ) {
        dto.isValidate(); // 논리 오류 확인
        Long memberId = userDetails.getId();
        Leaf leaf = dto.toLeaf();
        LeafBook LeafBook = dto.toLeafBook();
        List<Long> leafTagIds = dto.getTagIds();

        LeafBook saved = leafBookService.createFirstLeafBook(memberId, leaf, LeafBook, leafTagIds);

        BookLeafResponse response = BookLeafResponse.of(saved, "첫번째 도서 리프 생성 성공", HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/book/leaves/{parentLeafId}")
    public ResponseEntity<BookLeafResponse> createBookLeaf(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long parentLeafId,
            @Valid @RequestBody BookLeafRequest dto
    ) {
        dto.isValidate(); // 논리 오류 확인v
        Long memberId = userDetails.getId();

        if (!leafBookService.isOwner(memberId, parentLeafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        Leaf leaf = dto.toLeaf();
        LeafBook LeafBook = dto.toLeafBook();
        List<Long> leafTagIds = dto.getTagIds();

        LeafBook saved = leafBookService.createLeafBook(memberId, parentLeafId, leaf, LeafBook, leafTagIds);

        BookLeafResponse response = BookLeafResponse.of(saved, "도서 리프 생성 성공", HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/book/leaves/{leafId}")
    public ResponseEntity<BookLeafResponse> updateBookLeaf(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long leafId,
            @Valid @RequestBody BookLeafRequest dto
    ) {
        dto.isValidate(); // 논리 오류 확인
        Long memberId = userDetails.getId();

        if (!leafBookService.isOwner(memberId, leafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        Leaf leaf = dto.toLeaf();
        LeafBook LeafBook = dto.toLeafBook();
        List<Long> leafTagIds = dto.getTagIds();

        LeafBook saved = leafBookService.updateLeafBook(memberId, leafId, leaf, LeafBook, leafTagIds);

        BookLeafResponse response = BookLeafResponse.of(saved, "도서 리프 수정 성공", HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/book/leaves/{leafId}")
    public ResponseEntity<BookLeafResponse> deleteBookLeaf(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long leafId
    ) {
        Long memberId = userDetails.getId();

        if (!leafBookService.isOwner(memberId, leafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        leafBookService.deleteLeafBook(leafId);
        BookLeafResponse response = BookLeafResponse.of(leafId, "도서 리프 삭제 성공", HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/book/leaves/{leafId}/edit")
    public ResponseEntity<BookLeafEditResponse> getEdit(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long leafId
    ) {
        Long memberId = userDetails.getId();

        if (!leafBookService.isOwner(memberId, leafId)) {
            throw new IllegalArgumentException("해당 리프에 대한 권한이 없습니다.");
        }

        BookLeafEditResponse response = leafBookService.getEdit(leafId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}