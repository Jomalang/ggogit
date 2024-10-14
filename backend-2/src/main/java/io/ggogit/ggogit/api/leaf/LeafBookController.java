package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.api.leaf.dto.request.BookLeafRequest;
import io.ggogit.ggogit.api.leaf.dto.response.BookLeafResponse;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import io.ggogit.ggogit.domain.leaf.service.LeafBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LeafBookController {

    private final LeafBookService leafBookService;

    @PostMapping("/book/first/leafs")
    public ResponseEntity<BookLeafResponse> createFirstBookLeaf(
        @Valid @RequestBody BookLeafRequest dto
    ) {
        dto.isValidate(); // 논리 오류 확인
        Long memberId = 1L; // TODO: 로그인 정보에서 memberId 가져오기
        Leaf leaf = dto.toLeaf();
        LeafBook LeafBook = dto.toLeafBook();
        List<Long> leafTagIds = dto.getTagIds();

        LeafBook saved = leafBookService.createFirstLeafBook(memberId, leaf, LeafBook, leafTagIds);

        BookLeafResponse response = BookLeafResponse.of(saved, "첫번째 도서 리프 생성 성공");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/book/leafs/{parentLeafId}")
    public ResponseEntity<BookLeafResponse> createBookLeaf(
            @PathVariable Long parentLeafId,
            @Valid @RequestBody BookLeafRequest dto
    ) {
        dto.isValidate(); // 논리 오류 확인
        Long memberId = 1L; // TODO: 로그인 정보에서 memberId 가져오기
        Leaf leaf = dto.toLeaf();
        LeafBook LeafBook = dto.toLeafBook();
        List<Long> leafTagIds = dto.getTagIds();

        LeafBook saved = leafBookService.createLeafBook(memberId, parentLeafId, leaf, LeafBook, leafTagIds);

        BookLeafResponse response = BookLeafResponse.of(saved, "도서 리프 생성 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/book/leafs/{leafId}")
    public ResponseEntity<BookLeafResponse> updateBookLeaf(
            @PathVariable Long leafId,
            @Valid @RequestBody BookLeafRequest dto
    ) {
        dto.isValidate(); // 논리 오류 확인
        Leaf leaf = dto.toLeaf();
        Long memberId = 1L; // TODO: 로그인 정보에서 memberId 가져오기;
        LeafBook LeafBook = dto.toLeafBook();
        List<Long> leafTagIds = dto.getTagIds();

        LeafBook saved = leafBookService.updateLeafBook(memberId, leafId, leaf, LeafBook, leafTagIds);

        BookLeafResponse response = BookLeafResponse.of(saved, "도서 리프 수정 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/book/leafs/{leafId}")
    public ResponseEntity<BookLeafResponse> deleteBookLeaf(
            @PathVariable Long leafId
    ) {
        leafBookService.deleteLeafBook(leafId);
        BookLeafResponse response = BookLeafResponse.of(leafId, "도서 리프 삭제 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}