package io.ggogit.ggogit.api.book;

import io.ggogit.ggogit.api.book.dto.BookCategoryResponse;
import io.ggogit.ggogit.domain.book.service.BookCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book-categories")
@RequiredArgsConstructor
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    @GetMapping
    public ResponseEntity<BookCategoryResponse> getBookCategories(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(name="p", defaultValue = "1") int page,
            @RequestParam(name="s", defaultValue = "10") int size
    ) {
        BookCategoryResponse response = bookCategoryService.list(query, size, page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}