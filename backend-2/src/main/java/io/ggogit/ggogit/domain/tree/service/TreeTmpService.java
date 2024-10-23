package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.TreeTmp;

public interface TreeTmpService {

    void deleteTmpById(Long memberId);

    Long save(TreeTmp treeTmp, Long memberId, Long seedId, Long bookCategoryId, byte[] bytes, String originalFilename);
}
