package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TreeDetailResponse {
    List<LeafBranchResponse> items;
    int totalCount;


    public static TreeDetailResponse of(List<LeafBranchResponse> items, int totalCount) {
        return TreeDetailResponse.builder()
                .items(items)
                .totalCount(totalCount)
                .build();
    }
}
