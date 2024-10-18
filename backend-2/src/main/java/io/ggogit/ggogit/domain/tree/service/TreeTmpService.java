package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.TreeTmp;

import java.util.Optional;

public interface TreeTmpService {

    Optional<TreeTmp> tmpTreeSave(TreeTmp treeTmp);
    void deleteTmpById(Long memberId);

}
