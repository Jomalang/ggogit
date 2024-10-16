package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.api.member.session.SessionConst;
import io.ggogit.ggogit.api.tree.dto.TreeInfoResponse;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;

    @Override
    public void register(Tree tree) {
        if (tree != null)
            treeRepository.save(tree);
    }
    @Override
    public void update(Tree tree) {
        if (tree != null)
            treeRepository.save(tree);
    }
    @Override
    public void delete(Long treeId) {
        Tree tree = treeRepository.findById(treeId).orElse(null);
        if (tree != null) {
            tree.setIsDeleted(true);
            treeRepository.save(tree);
        }
    }
    @Override
    public Tree get(Long treeId) {return treeRepository.findById(treeId).orElse(null);}

    @Override
    public List<Tree> findAllByMemberId(Long memberId) { return  treeRepository.findByMemberId(memberId); }

    @Override
    public Boolean getComplate(Long treeId) {
        Tree tree = treeRepository.findById(treeId).orElse(null);
        Integer totalPage = tree.getBook().getTotalPage();
        Integer readingPage = tree.getTreeBook().getReadingPage();

        if (totalPage != null && readingPage != null) {
            return (readingPage * 100.0) / totalPage >= 80;
        }
        else return false;
    }


    @Override
    public Boolean isOwner(Long treeId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId = member.getId();
        Tree tree = treeRepository.findById(treeId).orElse(null);
        if (tree != null && userId != null) {
            return memberId.equals(tree.getMember().getId());
        }
        return false;
    }

    @Override
    public Integer getTreeCount(Long memberId) { return (treeRepository.findByMemberId(memberId)).size(); }

    @Override
    public Integer getLeafCount(Long treeId) {
        return 0;
    }

    @Override
    public Seed getSeedByTreeId(Long treeId) { return (treeRepository.findByTreeId(treeId)).getSeed(); }


    @Override
    public Long getMemberId(Long treeId) {
        return (treeRepository.findByTreeId(treeId)).getMember().getId(); }

}
