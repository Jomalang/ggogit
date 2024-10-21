package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.TreeTmp;

import java.util.Optional;

public interface TreeTmpService {

    Long tmpTreeSave(TreeTmp treeTmp, Member member,Long seedId, Long bookCategoryId, byte[] img, String imgFileName);
    void deleteTmpById(Long memberId);

}
