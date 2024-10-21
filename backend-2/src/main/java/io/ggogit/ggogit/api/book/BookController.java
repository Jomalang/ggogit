package io.ggogit.ggogit.api.book;

import io.ggogit.ggogit.api.book.dto.BookDetailResponse;
import io.ggogit.ggogit.api.book.dto.BookRequest;
import io.ggogit.ggogit.api.book.dto.BookResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 수정
    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponse> modify(
         @PathVariable Long bookId,
         @RequestBody BookRequest dto,
         MultipartFile imageFile
    ) {
        Long memberId = 1000L; // TODO: 로그인한 사용자의 ID를 가져와야 함

        if (!bookService.isOwner(bookId, memberId)) {
            throw new IllegalArgumentException("본인이 등록한 책만 수정할 수 있습니다.");
        }

        Book book = bookService.modify(bookId, dto.toEntity(), imageFile);
        BookResponse response = BookResponse.of(book, "책 정보가 수정되었습니다.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 디테일 조회
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDetailResponse>  detail(
        @PathVariable Long bookId
    ) {
        Book book = bookService.findById(bookId);
        BookDetailResponse response = BookDetailResponse.of(book);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 리스트 조회
}