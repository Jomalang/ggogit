package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.repository.LeafBookRepository;
import Recorders.ggogit.domain.leaf.repository.LeafRepository;
import Recorders.ggogit.domain.leaf.repository.LeafTagRepository;
import Recorders.ggogit.domain.leaf.structure.LeafNode;
import Recorders.ggogit.domain.leaf.structure.LeafTree;
import Recorders.ggogit.domain.leaf.view.*;
import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.repository.TreeRepository;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class LeafServiceImpl implements LeafService {

    @Autowired
    private LeafRepository leafRepository;

    @Autowired
    private LeafBookRepository leafBookRepository;

    @Autowired
    private TreeRepository treeRepository;

    @Autowired
    private LeafTagRepository leafTagRepository;

    @Override
    public List<LeafItemView> getLeafItems(Long treeId, @Nullable Long leafId, boolean isOwner) {
        List<Leaf> leafs =  leafRepository.findByTreeIdOrderById(treeId);
        LeafTree leafTree = new LeafTree(leafs);
        List<LeafNode> branchNodes = leafTree.findAll(leafId, isOwner);

        List<LeafItemView> leafItemViews = new ArrayList<>();
        for (LeafNode leafNode : branchNodes) {
            if (!leafNode.getData().getVisibility()) {
                continue; // 비공개 리프는 제외
            }
            List<LeafTag> leafTags = leafTagRepository.findByLeafId(leafNode.getData().getId());
            leafItemViews.add(LeafItemView.of(leafNode, leafTags));
        }

        // 트리 조회
        return leafItemViews;
    }

    @Override
    public LeafListBranchView getBranchInfo(Long treeId, Long leafId) {
        List<Leaf> leafs =  leafRepository.findByTreeIdOrderById(treeId);

        LeafTree leafTree = new LeafTree(leafs); // Tree 자료구조
        List<Leaf> branch = leafTree.getBranch(leafId);

        Long likeCount = 0L;
        Long viewCount = 0L;
        for (Leaf leaf : branch) {
            likeCount += leaf.getLikeCount();
            viewCount += leaf.getViewCount();
        }

        return LeafListBranchView.builder()
                .branchName(branch.getFirst().getTitle())
                .leafCount((long) branch.size())
                .likeCount(likeCount)
                .viewCount(viewCount)
                .updateTime(branch.getFirst().getCreateTime())
                .build();
    }

    @Override
    public LeafBreadcrumbView getBreadcrumb(Long treeId, Long leafId) {

        // 트리 조회
        Tree tree = Optional.ofNullable(treeRepository.findById(treeId))
                .orElseThrow(() -> new IllegalArgumentException("Tree 조회 실패"));

        // 리프 조회
        Leaf leaf = Optional.ofNullable(leafRepository.findById(leafId))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 조회 실패"));

        return LeafBreadcrumbView.builder()
                .treeName(tree.getTitle())
                .branchName(leaf.getTitle())
                .build();
    }

    @Override
    public LeafBreadcrumbView getBreadcrumb(Long leafId) {

        // 리프 조회
        Leaf leaf = Optional.ofNullable(leafRepository.findById(leafId))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 조회 실패"));

        // 리프의 부모 조회
        return getBreadcrumb(leaf.getTreeId(), leafId);
    }

    @Override
    public List<LeafCardView> getLeafCardViews(Long bookId, Long memberId) {
        return getLeafCardViews(bookId, memberId, SearchType.NONE, null, SortType.NONE, 1L, 10L);
    }

    @Override
    public List<LeafCardView> getLeafCardViews(Long bookId, Long memberId, SearchType searchType, String search, SortType sortType, Long page, Long size) {

        List<LeafCardView> leafCardViews = new ArrayList<>();

        // 리프 제목 및 내용 검색
        if (searchType == SearchType.ALL) {
            leafCardViews = leafRepository
                    .findLeafCardViewByBookId(bookId, memberId, SearchType.ALL, search, sortType, page, size);
        }
        // 리프 제목 검색
        else if (searchType == SearchType.TITLE) {
            leafCardViews = leafRepository
                    .findLeafCardViewByBookId(bookId, memberId, SearchType.TITLE, search, sortType, page, size);
        }
        // 리프 내용 검색
        else if (searchType == SearchType.CONTENT) {
            leafCardViews = leafRepository
                    .findLeafCardViewByBookId(bookId, memberId, SearchType.CONTENT, search, sortType, page, size);
        }

        return leafCardViews;
    }

    @Override
    public List<LeafNode> getLeafNodeFromLeafIdToEnd(Long treeId, Long leafId, boolean isOwner) {
        List<Leaf> leafs =  leafRepository.findByTreeIdOrderById(treeId);
        LeafTree leafTree = new LeafTree(leafs); // Tree 자료구조
        return leafTree.findToEnd(leafId, isOwner);
    }

    @Override
    public List<LeafNode> getLeafNodeAll(Long treeId, Long leafId, boolean isOwner) {
        List<Leaf> leafs =  leafRepository.findByTreeIdOrderById(treeId);
        LeafTree leafTree = new LeafTree(leafs); // Tree 자료구조
        return leafTree.findAll(leafId, isOwner);
    }

    @Override
    public BeforeLeafInfoView getBeforeLeafInfoView(Long leafId) {
        Leaf leaf = Optional.ofNullable(leafRepository.findById(leafId))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 조회 실패"));
        List<LeafTag> leafTags = leafTagRepository.findByLeafId(leafId);
        return BeforeLeafInfoView.of(leaf, leafTags);
    }

    @Override
    public boolean isOwner(Long treeId, Long memberId) {
        // 트리 검색
        Tree tree = Optional.ofNullable(treeRepository.findById(treeId))
                .orElseThrow(() -> new IllegalArgumentException("Tree 존재하지 않는 트리 아이디 입니다."));

        // 멤버 아이디 확인
        return Objects.equals(tree.getMemberId(), memberId);
    }

    @Override
    public List<LeafImageCardView> getLeafImageCardViews(Long memberId) {
        return getLeafImageCardViews(memberId, SearchType.NONE, null, SortType.NONE, 1L, 10L);
    }

    @Override
    public List<LeafImageCardView> getLeafImageCardViews(Long memberId, SearchType searchType, String search, SortType sortType, Long page, Long size) {

        List<LeafImageCardView> leafImageCardViews = new ArrayList<>();

        // 리프 제목 및 내용 검색
        if (searchType == SearchType.ALL) {
            leafImageCardViews = leafRepository
                    .findLeafImageCardViewByMemberId(memberId, SearchType.ALL, search, sortType, page, size);
        }
        // 리프 제목 검색
        else if (searchType == SearchType.TITLE) {
            leafImageCardViews = leafRepository
                    .findLeafImageCardViewByMemberId(memberId, SearchType.TITLE, search, sortType, page, size);
        }
        // 리프 내용 검색
        else if (searchType == SearchType.CONTENT) {
            leafImageCardViews = leafRepository
                    .findLeafImageCardViewByMemberId(memberId, SearchType.CONTENT, search, sortType, page, size);
        }

        return leafImageCardViews;
    }
}