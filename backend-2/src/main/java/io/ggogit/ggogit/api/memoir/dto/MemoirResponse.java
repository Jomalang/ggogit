package io.ggogit.ggogit.api.memoir.dto;

import io.ggogit.ggogit.api.book.dto.BookDetailResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoirResponse {

    private MemoirDto memoirDto;
    private BookDetailResponse bookDto;
    private String message;
    private boolean isOwner;

    //TODO:TREE DTO 추가하기
    public static MemoirResponse of(MemoirDto memoirDto, BookDetailResponse bookDto){
        return MemoirResponse.builder()
                .memoirDto(memoirDto)
                .bookDto(bookDto)
                .build();
    }
    public void ChangeOwnership(boolean ownership) {
        this.isOwner = ownership;
    }
}
