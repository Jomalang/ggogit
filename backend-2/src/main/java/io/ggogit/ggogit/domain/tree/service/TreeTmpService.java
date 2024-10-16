package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.TreeTmp;

public interface TreeTmpService {

    void tmpTreeSave(TreeTmp treeTmp);
    void deleteTmpById(Long memberId);

}
