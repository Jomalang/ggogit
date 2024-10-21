package io.ggogit.ggogit.api.book.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookListResponse {

    private int page;
    private List<BookDetailResponse> bookDetailResponse;
    private String message;

    public static BookListResponse of(List<BookDetailResponse> bookDetailResponse, String page) {
        return BookListResponse.builder()
                .bookDetailResponse(bookDetailResponse)
                .message(page+"페이지 : "+bookDetailResponse.size()+"건"+(bookDetailResponse.isEmpty() ? "조회결과가 없습니다." : "이 조회되었습니다."))
                .build();
    }
}
