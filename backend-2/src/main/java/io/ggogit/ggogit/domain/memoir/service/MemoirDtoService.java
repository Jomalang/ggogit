package io.ggogit.ggogit.domain.memoir.service;

import io.ggogit.ggogit.api.memoir.dto.MemoirCardDtoResponseList;

public interface MemoirDtoService {
    MemoirCardDtoResponseList getMemoirCardDtoResponseList(Long memberId);
}

