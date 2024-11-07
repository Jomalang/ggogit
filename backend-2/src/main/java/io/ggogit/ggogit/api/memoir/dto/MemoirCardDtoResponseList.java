package io.ggogit.ggogit.api.memoir.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class MemoirCardDtoResponseList {

    private List<MemoirCardDtoResponse> memoirCardDtoResponses;
    private long totalCount;

    public static MemoirCardDtoResponseList of(List<MemoirCardDtoResponse> memoirCardDtoResponses) {
        return MemoirCardDtoResponseList.builder()
                .memoirCardDtoResponses(memoirCardDtoResponses)
                .totalCount(memoirCardDtoResponses.size())
                .build();
    }
}
