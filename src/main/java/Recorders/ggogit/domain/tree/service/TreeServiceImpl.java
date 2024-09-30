package Recorders.ggogit.domain.tree.service;

import Recorders.ggogit.domain.leaf.repository.LeafRepository;
import Recorders.ggogit.domain.leaf.view.LeafBranchView;
import Recorders.ggogit.domain.member.repository.MemberRepository;
import Recorders.ggogit.domain.member.view.MemberImageView;
import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.repository.TreeRepository;
import Recorders.ggogit.domain.tree.repository.TreeSaveTmpRepository;
import Recorders.ggogit.domain.tree.view.BookTreeView;
import Recorders.ggogit.domain.tree.view.CombineTreeView;
import Recorders.ggogit.domain.tree.view.EtcTreeView;
import Recorders.ggogit.domain.tree.view.TreeInfoView;
import Recorders.ggogit.web.tree.form.TreeEtcSaveTmpForm;
import Recorders.ggogit.web.tree.form.TreeSaveTmpForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    @Autowired
    TreeRepository repository;

    @Autowired
    TreeSaveTmpRepository treeSaveTmpRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LeafRepository leafRepository;

    @Override
    public void register(Tree tree) {
        repository.save(tree);
    }

    @Override
    public List<TreeInfoView> getTreeInfoView(String str) {
        return List.of();
    }

    @Override
    public long hasTreeNumById(Long id) {
        return repository.hasTreeNumById(id);
    }

    @Override
    public List<TreeInfoView> getTreeInfoView(Long memberId) {
        return repository.getTreeInfoBookView(memberId);
    }

    @Override
    public List<BookTreeView> getBookTreeView(Long memberId) {
        return List.of();
    }

    @Override
    public List<EtcTreeView> getEtcTreeview(Long memberId) {
        return List.of();
    }

    @Override
    public Boolean getComplate(Long treeId) {
        return null;
    }

    @Override
    public void delete(Long treeId) {

    }

    public void setTreeImg(File img) {

    }

    public void tmpTreeSave(TreeSaveTmpForm form){
        treeSaveTmpRepository.save(form);

    }

    @Override
    public void tmpEtcTreeSave(TreeEtcSaveTmpForm form) {
        treeSaveTmpRepository.saveEtc(form);
    }

    @Override
    public void deleteTmpFormById(Long memberId) {
        treeSaveTmpRepository.deleteByMemberId(memberId);
    }

    @Override
    public TreeInfoView getTreeInfoViewByTreeId(Long treeId) {

        return repository.getTreeInfoViewByTreeId(treeId);
    }

    @Override
    public CombineTreeView setCombineTreeView(Long memberId, Long treeId) {

        System.out.println(memberId);
        System.out.println(treeId);

        MemberImageView memberImageView = memberRepository.getMemberImageView(memberId);
        TreeInfoView treeInfoView = repository.getTreeInfoViewByTreeId(treeId);
        List<LeafBranchView> leafList = leafRepository.findLeafBranchViewByTreeId(treeId);

        CombineTreeView combineTreeView = CombineTreeView.builder()
                .memberImageView(memberImageView)
                .treeInfoView(treeInfoView)
                .leafList(leafList)
                .build();

        return combineTreeView;
    }

    @Override
    public Integer getTreeCount(Long id) {
        return repository.getTreeCountByMemberId(id);
    }

    @Override
    public Long getSeedId(Long treeId) {
        Tree tree = repository.findById(treeId);
        return tree.getSeedId();
    }

}
