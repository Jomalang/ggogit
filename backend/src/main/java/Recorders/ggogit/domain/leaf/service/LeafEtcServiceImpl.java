package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.entity.LeafTagMap;
import Recorders.ggogit.domain.leaf.repository.LeafRepository;
import Recorders.ggogit.domain.leaf.repository.LeafTagMapRepository;
import Recorders.ggogit.domain.leaf.repository.LeafTagRepository;
import Recorders.ggogit.domain.leaf.view.LeafEtcView;
import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.entity.TreeImage;
import Recorders.ggogit.domain.tree.entity.TreeSaveTmp;
import Recorders.ggogit.domain.tree.repository.TreeImageRepository;
import Recorders.ggogit.domain.tree.repository.TreeRepository;
import Recorders.ggogit.domain.tree.repository.TreeSaveTmpRepository;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeafEtcServiceImpl implements LeafEtcService {

    private final static int LEAF_MAX_CHILD_COUNT = 3;

    private final LeafRepository leafRepository;
    private final LeafTagRepository leafTagRepository;
    private final LeafTagMapRepository leafTagMapRepository;

    private final TreeRepository treeRepository;
    private final TreeSaveTmpRepository treeSaveTmpRepository;
    private final TreeImageRepository treeImageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    @Transactional
    public LeafEtcView register(LeafEtcView leafEtcView, Long memberId) {
        if (leafEtcView.getParentLeafId() == null) {
            return registerRoot(leafEtcView, memberId);
        } else {
            return registerNode(leafEtcView, leafEtcView.getParentLeafId(), memberId);
        }
    }

    @Override
    @Transactional
    public LeafEtcView registerRoot(LeafEtcView leafEtcView, Long memberId) {
        assert leafEtcView.getParentLeafId() == null;

        // TMP트리 존재 확인
        TreeSaveTmp treeSaveTmp = Optional.ofNullable(treeSaveTmpRepository.findById(memberId))
                .orElseThrow(() -> new IllegalArgumentException("TMP Tree가 존재하지 않습니다."));

        // 기타 트리 저장
        Tree tree = treeSaveTmp.toTree();
        treeRepository.saveEtc(tree);
        leafEtcView.setTreeId(tree.getId());

        // 이미지가 있는 경우 이미지 저장
        if (treeSaveTmp.getImageFile() != null) {
            TreeImage treeImage = treeSaveTmp.toTreeImage();

            String toFileName = tree.getId() + ".jpg";
            moveImageFile(treeImage.getName(), toFileName);

            treeImage.setName(toFileName);
            treeImage.setTreeId(tree.getId());
            treeImageRepository.save(treeImage);
        }

        // TMP트리 삭제
        treeSaveTmpRepository.deleteByMemberId(memberId);

        return registerLogic(leafEtcView);
    }

    @Override
    @Transactional
    public LeafEtcView registerNode(LeafEtcView leafEtcView, Long parentLeafId, Long memberId) {
        assert leafEtcView.getParentLeafId() != null;

        // 부모 리프 존재 확인
        Leaf parentLeaf = Optional.ofNullable(leafRepository.findById(leafEtcView.getParentLeafId()))
                .orElseThrow(() -> new IllegalArgumentException("부모 Leaf 조회 실패"));

        // 부모 자식 개수 확인
        if (LEAF_MAX_CHILD_COUNT <= parentLeaf.getChildLeafCount()) {
            throw new IllegalArgumentException("부모 Leaf의 자식 개수가 최대치 3개를 초과했습니다.");
        }

        // 기타 리프 저장 로직
        registerLogic(leafEtcView);

        // 자식 수 증가
        parentLeaf.setChildLeafCount(parentLeaf.getChildLeafCount() + 1);

        // 부모 리프 업데이트
        Long parentLeafCheck = Optional.ofNullable(leafRepository.update(parentLeaf))
                .orElseThrow(() -> new IllegalArgumentException("부모 Leaf 업데이트 실패"));

        return leafEtcView;
    }

    private LeafEtcView registerLogic(LeafEtcView leafEtcView) {

        // 태그 존재 확인
        leafEtcView.getTags().forEach(tag -> {
            if (!leafTagRepository.existsById(tag.getId())) {
                throw new IllegalArgumentException("태그가 존재하지 않습니다.");
            }
        });

        Leaf leaf = leafEtcView.toLeaf();
        Long saveCheck = leafRepository.save(leaf);
        leafEtcView.setLeafId(leaf.getId());
        if (saveCheck != 1) {
            throw new IllegalArgumentException("Leaf저장에 실패했습니다.");
        }

        // 리프 태그 맵핑 저장
        leafEtcView.getTags().forEach(tag -> {
            Optional.ofNullable(leafTagMapRepository.save(LeafTagMap.of(leaf.getId(), tag.getId())))
                    .orElseThrow(() -> new IllegalArgumentException("LeafTagMap 저장 실패"));
        });

        return leafEtcView;
    }

    @Override
    @Transactional
    public boolean modify(LeafEtcView leafEtcView) {
        assert leafEtcView.getLeafId() != null;

        // 리프 조회
        Leaf leaf = Optional.ofNullable(leafRepository.findById(leafEtcView.getLeafId()))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 조회 실패"));

        // 리프 업데이트
        leaf.setTitle(leafEtcView.getTitle());
        leaf.setContent(leafEtcView.getContent());
        leaf.setVisibility(leafEtcView.getVisibility());

        // 리프 업데이트
        Long leafCheck = Optional.ofNullable(leafRepository.update(leaf))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 업데이트 실패"));

        // 도서 리프 태그 맵핑 기존 제거
        if (!leafEtcView.getTags().isEmpty()) { // 태그가 있을 때만
            List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeafId(leaf.getId());

            // 리프 태그 맵핑 제거
            for (LeafTagMap leafTagMap : leafTagMaps) { // TODO: REFACTORING 이 부분은 더 효율적으로 수정 가능
                leafTagMapRepository.delete(leafTagMap);
            }

            // 리프 태그 맵핑 추가
            for (LeafTag tag : leafEtcView.getTags()) {
                Optional.ofNullable(leafTagMapRepository.save(LeafTagMap.of(leaf.getId(), tag.getId())))
                        .orElseThrow(() -> new IllegalArgumentException("LeafTagMap 저장 실패"));
            }
        }

        return true;
    }

    @Override
    @Transactional
    public void remove(Long leafId) {
        // 리프 태그 맵핑 제거
        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeafId(leafId);
        for (LeafTagMap leafTagMap : leafTagMaps) {
            leafTagMapRepository.delete(leafTagMap);
        }

        // 리프 조회
        Leaf leaf = Optional.ofNullable(leafRepository.findById(leafId))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 조회 실패"));

        // 리프 삭제
        leafRepository.delete(leaf);
    }

    @Override
    @Transactional
    public LeafEtcView getLeafEtcView(Long leafId) {

        // 리프 조회
        Leaf leaf = Optional.ofNullable(leafRepository.findById(leafId))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 조회 실패"));

        // 리프 태그 맵핑 조회
        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeafId(leafId);

        // 리프 태그 조회
        List<LeafTag> tags = getLeafTags(leafTagMaps);
        return LeafEtcView.of(leaf, tags);
    }

    @Override
    public List<LeafEtcView> getLeafEtcViews(Long treeId) {
        return getLeafEtcViews(treeId, SearchType.NONE, null, SortType.NONE, 1L, 10L);
    }

    @Override // 트리 기준 리프 제목 검색
    public List<LeafEtcView> getLeafEtcViews(Long treeId, SearchType searchType, String search) {
        return getLeafEtcViews(treeId, searchType, search, SortType.NONE, 1L, 10L);
    }

    @Override
    public List<LeafEtcView> getLeafEtcViews(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size) {

        List<LeafEtcView> leafEtcViews = new ArrayList<>();

        // 리프 제목 및 내용 검색
        if (searchType == SearchType.ALL) {
            leafEtcViews = leafRepository
                    .findLeafEtcViewByTreeId(treeId, SearchType.ALL, search, sortType, page, size);
        }
        // 리프 제목 검색
        else if (searchType == SearchType.TITLE) {
            leafEtcViews = leafRepository
                    .findLeafEtcViewByTreeId(treeId, SearchType.TITLE, search, sortType, page, size);
        }
        // 리프 내용 검색
        else if (searchType == SearchType.CONTENT) {
            leafEtcViews = leafRepository
                    .findLeafEtcViewByTreeId(treeId, SearchType.CONTENT, search, sortType, page, size);
        }

        for (LeafEtcView leafEtcView : leafEtcViews) {
            List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeafId(leafEtcView.getLeafId());
            List<LeafTag> tags = getLeafTags(leafTagMaps);
            leafEtcView.setTags(tags);
        }

        return leafEtcViews;
    }

    @Override
    public boolean isOwner(Long leafId, Long memberId) {
        Leaf leaf = Optional.ofNullable(leafRepository.findById(leafId))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 조회 실패"));

        Tree tree = Optional.ofNullable(treeRepository.findById(leaf.getTreeId()))
                .orElseThrow(() -> new IllegalArgumentException("Tree 조회 실패"));

        return tree.getMemberId().equals(memberId);
    }

    @Override
    public LeafEtcView update(Long leafId, LeafEtcView leafEtcView, Long memberId) {

        Leaf leaf = Optional.ofNullable(leafRepository.findById(leafId))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 조회 실패"));

        Leaf toLeaf = leafEtcView.toLeaf();
        leaf.setTitle(toLeaf.getTitle());
        leaf.setContent(toLeaf.getContent());
        leaf.setVisibility(toLeaf.getVisibility());
        leafRepository.update(leaf);

        List<LeafTagMap> leafTagMap = leafTagMapRepository.findByLeafId(leafId);
        for (LeafTagMap tagMap : leafTagMap) { // 기존 태그 맵핑 삭제
            leafTagMapRepository.delete(tagMap);
        }

        List<Long> tagIds = leafEtcView.getTagIds();
        for (Long tagId : tagIds) { // 새로운 태그 맵핑 추가
            leafTagMapRepository.save(LeafTagMap.of(leafId, tagId));
        }

        return null;
    }

    private List<LeafTag> getLeafTags(List<LeafTagMap> leafTagMaps) { // 리프 태그 조회
        List<LeafTag> tags = new ArrayList<>();
        for (LeafTagMap leafTagMap : leafTagMaps) {
            LeafTag leafTag = Optional.ofNullable(leafTagRepository.findById(leafTagMap.getLeafTagId()))
                    .orElseThrow(() -> new IllegalArgumentException("LeafTag 조회 실패"));
            tags.add(leafTag);
        }
        return tags;
    }

    public void moveImageFile(String fromPath, String toFileName) {
        Path from = Path.of(fromPath);
        Path to = Paths.get(uploadDir, "image", "tree", toFileName);

        try {
            if (!Files.exists(to.getParent())) { // 폴더 경로 존재 확인
                Files.createDirectories(to.getParent());
            }
            Files.move(from, to);
        } catch (Exception e) {
            throw new IllegalArgumentException("이미지 파일 이동 실패", e);
        }
    }
}