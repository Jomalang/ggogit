package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.api.leaf.dto.*;
import io.ggogit.ggogit.api.member.dto.MemberInfoResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.leaf.entity.LeafTagMap;
import io.ggogit.ggogit.domain.leaf.repository.LeafBookRepository;
import io.ggogit.ggogit.domain.leaf.repository.LeafRepository;
import io.ggogit.ggogit.domain.leaf.repository.LeafTagMapRepository;
import io.ggogit.ggogit.domain.leaf.structure.TreeNode;
import io.ggogit.ggogit.domain.leaf.structure.TreeStructure;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeImage;
import io.ggogit.ggogit.domain.tree.repository.TreeImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeafDtoServiceImpl implements LeafDtoService {

    private final LeafRepository leafRepository;
    private final LeafBookRepository leafBookRepository;
    private final LeafTagMapRepository leafTagMapRepository;
    private final TreeImageRepository treeImageRepository;

    @Override
    @Transactional(readOnly = true)
    public LeafItemResponse getLeafNodeRootToEnd(Long leafId, boolean isOwner) {

        // 트리 아이디 기반 리프들 조회
        List<Leaf> leafNodes = getTreeLeafs(leafId);

        // 트리 생성
        TreeStructure treeStructure = new TreeStructure(leafNodes);

        // 트리에서 Root To End 까지 조회
        List<TreeNode> treeNodes = treeStructure.findAll(leafId, isOwner);

        // LeafItemResponse 로 변환
        return getLeafItemResponse(treeNodes, leafId);
    }

    @Override
    public LeafBranchInfoResponse getBranchInfo(Long leafId) {

        // 트리 아이디 기반 리프들 조회
        List<Leaf> leafNodes = getTreeLeafs(leafId);

        // 트리 생성
        TreeStructure treeStructure = new TreeStructure(leafNodes);
        List<TreeNode> treeNodes = treeStructure.findAll(leafId, true); // 브랜치 정보 조회는 비공개 리프도 조회

        int likeCount = 0;
        int viewCount = 0;
        for (TreeNode treeNode : treeNodes) {
            Leaf leaf = treeNode.getValue();
            likeCount += leaf.getLikeCount();
            viewCount += leaf.getViewCount();
        }

        return LeafBranchInfoResponse.builder()
                .branchName(treeNodes.getLast().getValue().getTitle())
                .leafCount(treeNodes.size())
                .likeCount(likeCount)
                .viewCount(viewCount)
                .updateTime(treeNodes.getFirst().getValue().getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public LeafItemToEndResponse getLeafNodeToEnd(Long leafId, boolean isOwner) {
        List<Leaf> leafNodes = getTreeLeafs(leafId);
        TreeStructure treeStructure = new TreeStructure(leafNodes);
        List<TreeNode> treeNodes = treeStructure.findToEnd(leafId, isOwner);
        return getLeafItemToEndResponse(treeNodes, leafId);
    }

    @Override
    public Page<Leaf> findLeafByTreeId(Long treeId, Boolean isOwner, Pageable pageable) {
        Page<Leaf> leafList = leafRepository.findByTreeId(treeId, isOwner, pageable);
        return leafList;
    }

    @Override
    public Leaf queryCheck(Long leafId) {
        return leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));
    }


    @Override
    public HashMap<String, Integer> nodeCountToRoot(Leaf leaf) {
        HashMap<String, Integer> result = new HashMap<>();
        Integer likeCnt = 0;
        Integer viewCnt = 0;
        Integer leafCnt = 1;
        Leaf parentLeaf, tmpLeaf;

        do {
            likeCnt += leaf.getLikeCount();
            viewCnt += leaf.getViewCount();
            leafCnt++;
            tmpLeaf = leaf;
            parentLeaf = leaf.getParentLeaf();
            leaf = parentLeaf;
        } while (!(tmpLeaf.getParentLeaf() == null));

        result.put("like", likeCnt);
        result.put("view", viewCnt);
        result.put("leaf", leafCnt);

        return result;
    }

    @Override
    public List<LeafBranchResponse> findBranchByFilter(Long treeId, Boolean owner, Boolean bookMark) {
        System.out.println("treeId = " + treeId);
        List<Leaf> leafList = leafRepository.findByBranchQuery(treeId, owner, bookMark);

        List<LeafBranchResponse> responseList = new ArrayList<>();
        for (Leaf leaf : leafList) {
            HashMap<String, Integer> result = nodeCountToRoot(leaf);

            Integer likeCnt = result.get("like");
            Integer viewCnt = result.get("view");
            Integer leafCnt = result.get("leaf");

            responseList.add(LeafBranchResponse.of(leaf, likeCnt, viewCnt, leafCnt));
        }


        for(LeafBranchResponse leaf : responseList){
            System.out.println(leaf.toString());
        }

        return responseList;
    }

    @Override
    @Transactional(readOnly = true)
    public LeafBookDetailResponse getBookDetail(Long leafId) {

        Leaf leaf = leafRepository.findByLeafId(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        LeafBook leafBook = leafBookRepository.findByLeaf(leaf)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프의 도서 정보가 존재하지 않습니다."));

        return LeafBookDetailResponse.of(leaf, leafBook);
    }

    @Override
    @Transactional(readOnly = true)
    public LeafBreadcrumbResponse getLeafBreadcrumb(Long leafId) {

        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        List<Leaf> leafNodes = leafRepository.findByTreeOrderById(leaf.getTree());

        TreeStructure treeStructure = new TreeStructure(leafNodes);
        List<TreeNode> treeNodes = treeStructure.findToEnd(leafId, true);
        return LeafBreadcrumbResponse.of(leaf, treeNodes.getLast());
    }

    @Override
    @Transactional(readOnly = true)
    public LeafBookEditDetailResponse getLeafBookEditDetail(Long leafId) {
        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        LeafBook leafBook = leafBookRepository.findByLeaf(leaf)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프의 도서 정보가 존재하지 않습니다."));

        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(leaf);
        List<LeafTag> leafTags = new ArrayList<>();
        for (LeafTagMap leafTagMap : leafTagMaps) {
            leafTags.add(leafTagMap.getLeafTag());
        }

        return LeafBookEditDetailResponse.of(leafBook, leaf, leafTags);
    }

    @Override
    @Transactional(readOnly = true)
    public LeafBeforeNodeInfoResponse getLeafBeforeNodeInfo(Long leafId) {

        // 리프 조회
        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(leaf);
        List<LeafTag> leafTags = new ArrayList<>();
        for (LeafTagMap leafTagMap : leafTagMaps) {
            leafTags.add(leafTagMap.getLeafTag());
        }

        return LeafBeforeNodeInfoResponse.of(leaf, leafTags);
    }

    @Override
    @Transactional(readOnly = true)
    public LeafEtcEditDetailResponse getEtcLeafEditDetail(Long leafId) {
        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(leaf);
        List<LeafTag> leafTags = new ArrayList<>();
        for (LeafTagMap leafTagMap : leafTagMaps) {
            leafTags.add(leafTagMap.getLeafTag());
        }

        return LeafEtcEditDetailResponse.of(leaf, leafTags);
    }

    @Override
    @Transactional(readOnly = true)
    public LeafEtcDetailResponse getLeafEtcDetail(Long leafId) {

        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        return LeafEtcDetailResponse.of(leaf);
    }

    @Override
    @Transactional(readOnly = true)
    public LeafBookCardResponse getLeafBookCards(Long memberId, int page, int size) {
        Page<Leaf> leafPage = leafRepository.getBookCards(memberId, page, size);

        int currentPage = leafPage.getNumber();
        int totalPage = leafPage.getTotalPages();
        LeafBookCardResponse response = new LeafBookCardResponse(totalPage, currentPage, size);

        for (Leaf leaf : leafPage.getContent()) {
            Long seedId = leaf.getTree().getSeed().getId();
            Tree tree = leaf.getTree();

            if (seedId == 1L) {
                Book book = tree.getBook();
                BookCategory bookCategory = book.getBookCategory();
                LeafBookCardResponse.ItemDto dto = LeafBookCardResponse.ItemDto.of(leaf, tree, book, bookCategory);
                response.addItem(dto);
            } else {
                TreeImage treeImage = treeImageRepository.findById(tree.getId()).orElse(null); // 트리 이미지가 없는 경우도 존재함
                LeafBookCardResponse.ItemDto dto = LeafBookCardResponse.ItemDto.of(leaf, tree, treeImage);
                response.addItem(dto);
            }
        }
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public LeafCardResponse getLeafCards(Long bookId, int page, int size) {
        Page<Leaf> leafPage = leafRepository.getLeafCards(bookId, page, size);

        int currentPage = leafPage.getNumber();
        int totalPage = leafPage.getTotalPages();
        LeafCardResponse response = new LeafCardResponse(totalPage, currentPage, size);

        for (Leaf leaf : leafPage.getContent()) {
            Tree tree = leaf.getTree();
            Member member = tree.getMember();
            LeafCardResponse.ItemDto itemDto = LeafCardResponse.ItemDto.of(leaf, tree, member);
            response.addItem(itemDto);
        }

        return response;
    }

    @Override
    public String getSeedType(Long leafId) {
        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));
        return leaf.getTree().getSeed().getId().equals(1L) ? "book" : "etc";
    }

    @Override
    public int getBookPage(Long leafId) {
        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        Tree tree = leaf.getTree();

        if (!tree.getSeed().getId().equals(1L)) {
            throw new IllegalArgumentException("해당 리프는 도서가 아닙니다.");
        }

        return tree.getBook().getTotalPage();
    }

    @Override
    public Tree getTree(Long leafId) {
        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));
        return leaf.getTree();
    }

    @Override
    public LeafDetailResponse getLeafDetail(Long leafId) {
        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        LeafBook leafBook = leafBookRepository.findByLeaf(leaf)
                .orElse(null);

        return LeafDetailResponse.of(leaf, leafBook);
    }

    @Override
    public MemberInfoResponse getMemberInfo(Long leafId) {
        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        Member member = leaf.getTree().getMember();
        return MemberInfoResponse.ofNoEmailDomain(member);
    }

    private LeafItemToEndResponse getLeafItemToEndResponse(List<TreeNode> treeNodes, Long leafId) {
        LeafItemToEndResponse response = new LeafItemToEndResponse();
        for (TreeNode treeNode : treeNodes) {
            // 리프 노드의 태그 조회
            List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(treeNode.getValue());
            List<LeafTag> leafTags = new ArrayList<>();
            for (LeafTagMap leafTagMap : leafTagMaps) {
                leafTags.add(leafTagMap.getLeafTag());
            }
            response.addItem(treeNode, leafTags, leafId);
        }
        return response;
    }

    private LeafItemResponse getLeafItemResponse(List<TreeNode> treeNodes, Long leafId) {
        LeafItemResponse response = new LeafItemResponse();
        for (TreeNode treeNode : treeNodes) {
            // 리프 노드의 태그 조회
            List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(treeNode.getValue());
            List<LeafTag> leafTags = new ArrayList<>();
            for (LeafTagMap leafTagMap : leafTagMaps) {
                leafTags.add(leafTagMap.getLeafTag());
            }
            response.addItem(treeNode, leafTags, leafId);
        }
        return response;
    }

    private List<Leaf> getTreeLeafs(Long leafId) {
        // 리프 조회
        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        // 트리 아이디 기반 리프들 조회
        return leafRepository.findByTreeOrderById(leaf.getTree());
    }

}