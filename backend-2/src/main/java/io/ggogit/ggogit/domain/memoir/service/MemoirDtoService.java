package io.ggogit.ggogit.domain.memoir.service;

import io.ggogit.ggogit.api.memoir.dto.MemoirBookCardDtoResponseList;
import io.ggogit.ggogit.api.memoir.dto.MemoirCardDtoResponse;

public interface MemoirDtoService {
    MemoirBookCardDtoResponseList getMemoirBookCardDtoResponseList(Long memberId);

    MemoirCardDtoResponse getMemoirCardDtoResponse(Long bookId, int page, int size);
}

