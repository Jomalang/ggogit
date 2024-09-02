package Recorders.ggogit.domain.tree.service;


import Recorders.ggogit.domain.tree.view.Vm_FindTreeInfo;

import java.util.List;

public interface TreeService {

    List<Vm_FindTreeInfo> findBymemberId(long memberId);
}
