package io.ggogit.ggogit.api.tree.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class TreeBookCardResponseList {

    @Builder.Default
    private List<TreeBookCardResponse> treeBookCardResponse = new ArrayList<>();
    private long totalCount;

    public static TreeBookCardResponseList of(List<TreeBookCardResponse> treeBookCardResponse) {
        return TreeBookCardResponseList.builder()
                .treeBookCardResponse(treeBookCardResponse)
                .totalCount(treeBookCardResponse.size())
                .build();
    }
}
