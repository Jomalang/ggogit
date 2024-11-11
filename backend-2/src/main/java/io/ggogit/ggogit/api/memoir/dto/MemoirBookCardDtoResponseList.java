package io.ggogit.ggogit.api.memoir.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class MemoirBookCardDtoResponseList {

    private List<MemoirBookCardDtoResponse> memoirBookCardDtoResponse;
    private long totalCount;

    public static MemoirBookCardDtoResponseList of(List<MemoirBookCardDtoResponse> memoirBookCardDtoRespons) {
        return MemoirBookCardDtoResponseList.builder()
                .memoirBookCardDtoResponse(memoirBookCardDtoRespons)
                .totalCount(memoirBookCardDtoRespons.size())
                .build();
    }
}
