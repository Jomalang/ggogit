package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class TreeDetailResponse {
    Page<LeafBranchResponse> items;
    int totalCnt;
    int totalPage;

    public static TreeDetailResponse toEntity(Page<LeafBranchResponse> items, int totalCnt, int totalPage) {
        return TreeDetailResponse.builder()
                .items(items)
                .totalCnt(totalCnt)
                .totalPage(totalPage)
                .build();
    }
}
