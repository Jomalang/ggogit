package io.ggogit.ggogit.api.tree.dto;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class TreeDetailResponse {
    Page<LeafBranchResponse> items;

    public static TreeDetailResponse of(Page<LeafBranchResponse> items) {
        return TreeDetailResponse.builder()
                .items(items)
                .build();
    }
}
