package Recorders.ggogit.domain.tree.service;


import Recorders.ggogit.domain.tree.view.FindTreeInfoView;
import Recorders.ggogit.domain.tree.view.MyTreeListsView;

import java.util.List;

public interface TreeService {

    List<FindTreeInfoView> treeInfoLists(Long memberId);
    List<MyTreeListsView> treeAllLists(Long memberId);
}
