package io.ggogit.ggogit.api.book.dto;

import io.ggogit.ggogit.domain.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookListResponse {

    private List<BookDetailResponse> books;
    private Integer page;
    private Integer totalCount;
    private Integer totalPage;

    public static BookListResponse of(List<BookDetailResponse> books, Integer page, Integer totalCount, Integer totalPage) {
        return BookListResponse.builder()
                .books(books)
                .page(page)
                .totalCount(totalCount)
                .totalPage(totalPage)
                .build();
    }

    public static BookListResponse of(List<Book> books) {
        return BookListResponse.builder()
                .books(books.stream() .map(BookDetailResponse::of).toList())
                .page(1)
                .totalCount(books.size())
                .totalPage(1)
                .build();
    }
}