package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.TreeSaveTmp;

public interface TreeSaveTmpService {
    TreeSaveTmp get(Long memberId);
    void save(TreeSaveTmp treeSaveTmp);
    void delete(Long memberId);
}
