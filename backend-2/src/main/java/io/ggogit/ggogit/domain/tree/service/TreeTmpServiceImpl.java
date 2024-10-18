package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.TreeTmp;
import io.ggogit.ggogit.domain.tree.repository.TreeTmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreeTmpServiceImpl implements TreeTmpService {

    private final TreeTmpRepository treeTmpRepository;
    @Override
    public Optional<TreeTmp> tmpTreeSave(TreeTmp treeTmp) {
        treeTmpRepository.save(treeTmp);
        return treeTmpRepository.findByMemberId(treeTmp.getMember().getId());
    }

    @Override
    public void deleteTmpById(Long memberId) {
        if (treeTmpRepository.findByMemberId(memberId).isPresent())
            treeTmpRepository.deleteById(memberId);
    }
}
