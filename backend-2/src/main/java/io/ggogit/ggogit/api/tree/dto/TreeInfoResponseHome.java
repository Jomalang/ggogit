package io.ggogit.ggogit.api.tree.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TreeInfoResponseHome {

    private final List<TreeInfoResponse> treeInfoResponseList = new ArrayList<>();

    public void addTreeInfoResponse(TreeInfoResponse treeInfoResponse) {
        treeInfoResponseList.add(treeInfoResponse);
    }
}
