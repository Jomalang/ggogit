package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeSaveTmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final TreeSaveTmpRepository treeSaveTmpRepository;

    @Override
    public void register(Tree tree) {
        treeRepository.save(tree);
    }

    @Override
    public Boolean getComplate(Long treeId) {
        return null;
    }

    @Override
    public void delete(Long treeId) {
        treeRepository.deleteById(treeId);
    }

    @Override
    public void deleteTmpFormById(Long memberId) {
        treeSaveTmpRepository.deleteById(memberId);
    }

    @Override
    public Integer getTreeCount(Long memberId) {
        return treeRepository.findByMemberId(memberId).stream().count();
    }

    @Override
    public Long getSeedId(Long treeId) {
        return 0;
    }

    @Override
    public Long toMemberId(Long treeId) {
        return 0;
    }
}
