package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.api.leaf.dto.LeafBeforeInfoResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafDetailResponse;

public interface LeafDtoService {

    /**
     * 리프 생성시 이전 정보 조회
     * @param leafId
     * @return
     */
    LeafBeforeInfoResponse findLeafBeforeInfo(Long leafId);


    /**
     * 리프 책 디테일 정보 조회
     * @param leafId
     * @return
     */
    LeafDetailResponse findLeafBookDetail(Long leafId);
}
