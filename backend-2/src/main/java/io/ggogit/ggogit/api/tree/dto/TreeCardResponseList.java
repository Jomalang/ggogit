package io.ggogit.ggogit.api.tree.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class TreeCardResponseList {

    private List<TreeCardResponse> treeCardResponses = new ArrayList<>();
    private long totalCount;

    public static TreeCardResponseList of(List<TreeCardResponse> treeCardResponse) {
        return TreeCardResponseList.builder()
                .treeCardResponses(treeCardResponse)
                .totalCount(treeCardResponse.size())
                .build();
    }
}
