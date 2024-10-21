package io.ggogit.ggogit.api.book;

import io.ggogit.ggogit.api.book.dto.AladinApiSearchResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.service.AladinService;
import io.ggogit.ggogit.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("aladin")
@RequiredArgsConstructor
public class AladinController {

    private final AladinService aladinService;
    private final BookService bookService;

    // API 키워드 검색
    @PostMapping
    public ResponseEntity<AladinApiSearchResponse> search(
            @RequestParam String query
    ) {
        List<Book> books = aladinService.fetchBookDataApi(query);
        int count = bookService.saveAll(books);

        AladinApiSearchResponse response = AladinApiSearchResponse
                .of(count, "알라딘 API를 통해 책 정보를 가져왔습니다.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}