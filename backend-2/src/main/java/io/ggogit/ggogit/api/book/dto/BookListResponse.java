package io.ggogit.ggogit.api.book.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookListResponse {

    List<BookDetailResponse> books;
    String page;

    public static BookListResponse of(List<BookDetailResponse> books, String page) {
        BookListResponse response = new BookListResponse();
        response.books = books;
        response.page = page;
        return response;
    }
}
