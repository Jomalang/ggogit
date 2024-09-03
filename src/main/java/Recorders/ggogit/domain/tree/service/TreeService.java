package Recorders.ggogit.domain.tree.service;


import Recorders.ggogit.domain.tree.view.FindTreeInfoView;

import java.util.List;

public interface TreeService {

    List<FindTreeInfoView> treeInfoLists(long memberId);
}
