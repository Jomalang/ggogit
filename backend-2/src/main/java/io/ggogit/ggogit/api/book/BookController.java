package io.ggogit.ggogit.api.book;

import io.ggogit.ggogit.api.book.dto.BookDetailResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("books/{bookId}")
    public ResponseEntity<BookDetailResponse> getBookDetail(
            @PathVariable(value = "bookId") Long bookId
    ) {
        Book book = bookService.findById(bookId);

        BookDetailResponse response = BookDetailResponse.of(book);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}