package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.entity.LeafTagMap;
import Recorders.ggogit.domain.leaf.repository.LeafRepository;
import Recorders.ggogit.domain.leaf.repository.LeafTagMapRepository;
import Recorders.ggogit.domain.leaf.repository.LeafTagRepository;
import Recorders.ggogit.domain.leaf.view.LeafEtcView;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.domain.leaf.entity.Leaf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeafEtcServiceImpl implements LeafEtcService {

    private final static int LEAF_MAX_CHILD_COUNT = 3;

    @Autowired
    private LeafRepository leafRepository;

    @Autowired
    private LeafTagRepository leafTagRepository;

    @Autowired
    private LeafTagMapRepository leafTagMapRepository;

    @Override
    public boolean register(LeafEtcView leafEtcView) {
        if (leafEtcView.getParentLeafId() == null) {
            return registerRoot(leafEtcView);
        } else {
            return registerNode(leafEtcView, leafEtcView.getParentLeafId());
        }
    }

    @Override
    public boolean registerRoot(LeafEtcView leafEtcView) {
        assert leafEtcView.getParentLeafId() == null;
        return registerLogic(leafEtcView);
    }

    @Override
    public boolean registerNode(LeafEtcView leafEtcView, Long parentLeafId) {
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

        return parentLeafCheck != null;
    }

    private boolean registerLogic(LeafEtcView leafEtcView) {

        // 태그 존재 확인
        leafEtcView.getTags().forEach(tag -> {
            if (!leafTagRepository.existsById(tag.getId())) {
                throw new IllegalArgumentException("태그가 존재하지 않습니다.");
            }
        });

        // 리프 저장
        Long leafId = Optional.ofNullable(leafRepository.save(leafEtcView.toLeaf()))
                .orElseThrow(() -> new IllegalArgumentException("리프 저장에 실패했습니다."));

        // 리프 태그 맵핑 저장
        leafEtcView.getTags().forEach(tag -> {
            Optional.ofNullable(leafTagMapRepository.save(LeafTagMap.of(leafId, tag.getId())))
                    .orElseThrow(() -> new IllegalArgumentException("LeafTagMap 저장 실패"));
        });

        return leafId != null;
    }

    @Override
    public void modify(LeafEtcView leafEtcView) {
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

    }

    @Override
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
    public Leaf get(Long leafId) {
        return null;
    }

    @Override
    public List<Leaf> list(Long treeId) {
        return List.of();
    }

    @Override
    public List<Leaf> list(Long treeId, SearchType searchType, String search) {
        return List.of();
    }
}