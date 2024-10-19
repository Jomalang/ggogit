package io.ggogit.ggogit.api.book;

import io.ggogit.ggogit.api.book.dto.BookDetailResponse;
import io.ggogit.ggogit.api.book.dto.BookListResponse;
import io.ggogit.ggogit.api.book.dto.BookRequest;
import io.ggogit.ggogit.api.book.dto.BookResponse;
import io.ggogit.ggogit.api.book.filter.BookFilterType;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;
    // 목록 조회
    @GetMapping
    public ResponseEntity<BookListResponse> getList(
            @RequestParam(name="p", required=true, defaultValue="1") int page,
            @RequestParam(name="q", required=false) String query,
            //TODO: 한글은 안될거같음.. 영어쓰면서도 상수화시켜야함.
            @RequestParam(name="f", required=true, defaultValue="제목") String filterName
    ) {
        log.info("query={}", query);

        if(query == null || query.length() < 2) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String filter = BookFilterType.findNameByDecription(filterName);
        List<Book> books = bookService.getBooks(page, query, filter);
        if(books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<BookDetailResponse> list = books.stream().map(BookDetailResponse::of).toList();
        BookListResponse response = BookListResponse.of(list, String.valueOf(page));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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