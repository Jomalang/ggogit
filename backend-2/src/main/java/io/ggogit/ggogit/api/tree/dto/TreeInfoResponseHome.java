package io.ggogit.ggogit.api.tree.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class TreeInfoResponseHome {

    private List<TreeInfoResponse> treeInfoResponseList = new ArrayList<>();

    public static TreeInfoResponseHome of(List<TreeInfoResponse> treeInfoResponseList) {
        return TreeInfoResponseHome.builder()
                .treeInfoResponseList(treeInfoResponseList)
                .build();
    }
}
