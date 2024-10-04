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

import java.time.LocalDateTime;
import java.util.*;

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
    public List<LeafItemView> getLeafItems(Long treeId, @Nullable Long leafId) {
        List<Leaf> leafs =  leafRepository.findByTreeIdOrderById(treeId);
        LeafTree leafTree = new LeafTree(leafs); // Tree 자료구조
        List<LeafNode> branchNodes = leafTree.findAll(leafId);

        List<LeafItemView> leafItemViews = new ArrayList<>();
        for (LeafNode leafNode : branchNodes) {
            List<LeafTag> leafTags = leafTagRepository.findByLeafId(leafNode.getData().getId());

            leafItemViews.add(LeafItemView.of(leafNode, leafTags));
        }
        leafItemViews.getLast().setFocused(true); // 마지막 리프에 포커스

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
    public List<LeafNode> getLeafNodeFromLeafIdToEnd(Long treeId, Long leafId) {
        List<Leaf> leafs =  leafRepository.findByTreeIdOrderById(treeId);
        LeafTree leafTree = new LeafTree(leafs); // Tree 자료구조
        return leafTree.findToEnd(leafId);
    }

    @Override
    public List<LeafNode> getLeafNodeAll(Long treeId, Long leafId) {
        List<Leaf> leafs =  leafRepository.findByTreeIdOrderById(treeId);
        LeafTree leafTree = new LeafTree(leafs); // Tree 자료구조
        return leafTree.findAll(leafId);
    }

    @Override
    public BeforeLeafInfoView getBeforeLeafInfoView(Long leafId) {
        Leaf leaf = Optional.ofNullable(leafRepository.findById(leafId))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 조회 실패"));
        List<LeafTag> leafTags = leafTagRepository.findByLeafId(leafId);
        return BeforeLeafInfoView.of(leaf, leafTags);
    }

    @Override
    public List<LeafBranchView> findBranchByTreeId(Long treeId) {
        List<Leaf> plainList = leafRepository.findByTreeIdOrderById(treeId);
        Map<Long,Leaf> leafMap = new HashMap<>();
        Long pid = null; //부모 ID 값이 없을 경우 DB에서 Null 반환.

        List<Leaf> tmpList = new ArrayList<>();
        for (Leaf leaf : plainList) {
            leafMap.put(leaf.getId(), leaf);
            if (leaf.getBookMark() || leaf.getChildLeafCount() == 0) {
                tmpList.add(leaf);
            }
        }


        System.out.println("leafMap : " + leafMap);
        List<LeafBranchView> leafBranchViews = new ArrayList<>();
        for (Leaf leaf : tmpList){
            pid = leaf.getParentLeafId();
            Long viewCount = leaf.getViewCount();
            Long leafCount = 0L;
            while (pid != null){
                Leaf tmp = leafMap.get(pid);
                pid = tmp.getParentLeafId();
                viewCount += tmp.getViewCount();
                ++leafCount;

                System.out.println("tmp : " + tmp);
            }
            LeafBranchView leafBranch = LeafBranchView.of(leaf, leafCount, viewCount);
            leafBranchViews.add(leafBranch);
        }


        return leafBranchViews;
    }


    @Override
    public List<LeafBranchView> filterBranchLsit(Boolean isLeaf, List<LeafBranchView> everyList) {
        /*
        * 리스트 분류
        * null = 모두
        * true = 책갈피
        * false = 라스트 리프
        * */
        List<LeafBranchView> filteredList = new ArrayList<>();
        if(isLeaf == null){
            return everyList;
        }else if (isLeaf){
            for (LeafBranchView leafBranchView : everyList)
                if(leafBranchView.getBookMark())
                    filteredList.add(leafBranchView);
        }else {
            for (LeafBranchView leafBranchView : everyList)
                if(!leafBranchView.getBookMark())
                    filteredList.add(leafBranchView);
        }
        return filteredList;
    }

    @Override
    public List<LeafBranchView> sortBranchList(Long sort, List<LeafBranchView> everyList) {
        /*
        * 브랜치 및 책갈피 정렬 메서드
        * 브랜치와 책갈피, 브랜치, 책갈피
        * 0. 업데이트 날짜 / LeafBranchView.getUpdateTime() / 내림순
        * 1. 업데이트 날짜 / LeafBranchView.getUpdateTime() / 오름순 /
        * 2. 제목 / LeafBranchView.getTitle() / 내림순
        * 3. 제목 / LeafBranchView.getTitle() / 오름순
        * 4. 리프 수 / LeafBranchView.getLeafCount() / 내림순
        * 5. 리프 수 / LeafBranchView.getLeafCount() / 오름순
        * 6. 조회 수 / LeafBranchView.getViewCount() / 내림순
        * 7. 조회 수 / LeafBranchView.getViewCount() / 오름순
        */
        switch (sort.intValue()) {
            case 0 -> everyList.sort((o1, o2) -> o2.getUpdateTime().compareTo(o1.getUpdateTime()));
            case 1 -> everyList.sort(Comparator.comparing(LeafBranchView::getUpdateTime));
            case 2 -> everyList.sort((o1, o2) -> o2.getTitle().compareTo(o1.getTitle()));
            case 3 -> everyList.sort(Comparator.comparing(LeafBranchView::getTitle));
            case 4 -> everyList.sort((o1, o2) -> o2.getLeafCount().compareTo(o1.getLeafCount()));
            case 5 -> everyList.sort(Comparator.comparing(LeafBranchView::getLeafCount));
            case 6 -> everyList.sort((o1, o2) -> o2.getViewCount().compareTo(o1.getViewCount()));
            case 7 -> everyList.sort(Comparator.comparing(LeafBranchView::getViewCount));

        }
        return everyList;
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