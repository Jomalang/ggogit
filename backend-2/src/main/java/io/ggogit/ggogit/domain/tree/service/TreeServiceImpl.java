package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.api.tree.dto.TreeSaveTmpRequest;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeSaveTmp;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeSaveTmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
        Tree tree = treeRepository.findById(treeId).orElse(null);
        Long totalPage = null;
        Long readingPage = null;

        if (tree != null) {
            totalPage = tree.getBook().getTotalPage();
            readingPage = tree.getTreeBook().getReadingPage();
            return (readingPage * 100.0) / totalPage >= 80;
        }
        else return false;
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
    public Integer getTreeCount(Long memberId) { return (treeRepository.findByMemberId(memberId)).size(); }

    @Override
    public Long getSeedId(Long treeId) { return (treeRepository.findByTreeId(treeId)).getSeed().getId(); }

    @Override
    public Long toMemberId(Long treeId) {
        return (treeRepository.findByTreeId(treeId)).getMember().getId(); }

    @Override
    public void tmpTreeSave(TreeSaveTmp treeSaveTmp) {treeSaveTmpRepository.save(treeSaveTmp);}
}
