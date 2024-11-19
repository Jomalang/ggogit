package io.ggogit.ggogit.api.tree.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeListHome {
    List<TreeInfoResponse> treeInfoResponseList;
    long totalCnt;

    public TreeListHome of(List<TreeInfoResponse> treeInfoResponse, long totalCnt) {
        return TreeListHome.builder()
                .treeInfoResponseList(treeInfoResponse)
                .totalCnt(totalCnt)
                .build();
    }

}
