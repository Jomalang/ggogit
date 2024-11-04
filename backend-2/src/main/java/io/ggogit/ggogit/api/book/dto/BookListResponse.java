package io.ggogit.ggogit.api.book.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookListResponse {

    List<BookDetailResponse> books;
    String page;
    String totalCount;
    String totalPage;

    public static BookListResponse of(List<BookDetailResponse> books, String page
            ,String totalCount, String totalPage) {
        BookListResponse response = new BookListResponse();
        response.books = books;
        response.page = page;
        response.totalCount = totalCount;
        response.totalPage = totalPage;
        return response;
    }
}
