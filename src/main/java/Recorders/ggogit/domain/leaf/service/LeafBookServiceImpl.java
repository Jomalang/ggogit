package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.Leaf;
import Recorders.ggogit.domain.leaf.entity.LeafBook;
import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.entity.LeafTagMap;
import Recorders.ggogit.domain.leaf.repository.LeafBookRepository;
import Recorders.ggogit.domain.leaf.repository.LeafRepository;
import Recorders.ggogit.domain.leaf.repository.LeafTagMapRepository;
import Recorders.ggogit.domain.leaf.repository.LeafTagRepository;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import Recorders.ggogit.domain.leaf.view.LeafCardView;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeafBookServiceImpl implements LeafBookService {

    private final static int LEAF_MAX_CHILD_COUNT = 3;

    @Autowired
    private LeafRepository leafRepository;

    @Autowired
    private LeafTagRepository leafTagRepository;

    @Autowired
    private LeafBookRepository leafBookRepository;

    @Autowired
    private LeafTagMapRepository leafTagMapRepository;

    @Override
    public boolean register(LeafBookView leafBookView) {
        if (leafBookView.getParentLeafId() == null) {
            return registerRoot(leafBookView);
        } else {
            return registerNode(leafBookView);
        }
    }

    @Override
    public boolean registerRoot(LeafBookView leafBookView) {
        assert leafBookView.getParentLeafId() == null; // 부모 리프가 없어야 함
        return registerLogic(leafBookView);
    }

    @Override
    public boolean registerNode(LeafBookView leafBookView) {
        assert leafBookView.getParentLeafId() != null; // 부모 리프가 있어야 함

        // 부모 리프 조회
        Leaf parentLeaf = Optional.ofNullable(leafRepository.findById(leafBookView.getParentLeafId()))
                .orElseThrow(() -> new IllegalArgumentException("부모 Leaf 조회 실패"));

        // 부모 자식 개수 확인
        if (LEAF_MAX_CHILD_COUNT <= parentLeaf.getChildLeafCount()) {
            throw new IllegalArgumentException("부모 Leaf의 자식 개수가 최대치 3개를 초과했습니다.");
        }

        // 도서 리프 저장 로직
        registerLogic(leafBookView);

        // 자식 수 증가
        parentLeaf.setChildLeafCount(parentLeaf.getChildLeafCount() + 1);

        // 부모 리프 저장
        Long parentLeafCheck = Optional.ofNullable(leafRepository.update(parentLeaf))
                .orElseThrow(() -> new IllegalArgumentException("부모 Leaf 수정 실패"));

        return parentLeafCheck != null;
    }

    private boolean registerLogic(LeafBookView leafBookView) {

        // 태그 존재 확인
        leafBookView.getTags().forEach(tag -> {
            if (!leafTagRepository.existsById(tag.getId())) {
                throw new IllegalArgumentException("태그가 존재하지 않습니다.");
            }
        });

        // 리프 저장
        Long leafId = Optional.ofNullable(leafRepository.save(leafBookView.toLeaf()))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 저장 실패"));

        // 도서 리프 저장
        Long bookId = Optional.ofNullable(leafBookRepository.save(leafBookView.toLeafBook(leafId)))
                .orElseThrow(() -> new IllegalArgumentException("LeafBook 저장 실패"));

        // 리프 태그 맵핑 저장
        leafBookView.getTags().forEach(tag -> {
            Optional.ofNullable(leafTagMapRepository.save(LeafTagMap.of(leafId, tag.getId())))
                    .orElseThrow(() -> new IllegalArgumentException("LeafTagMap 저장 실패"));
        });

        // 결과 확인
        return leafId != null && bookId != null;
    }

    @Override
    public boolean modify(LeafBookView leafBookView) {
        assert leafBookView.getLeafId() != null; // ID가 있어야 함

        // 리프 조회
        Leaf leaf = Optional.ofNullable(leafRepository.findById(leafBookView.getLeafId()))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 조회 실패"));

        // 리프 수정
        leaf.setTitle(leafBookView.getTitle());
        leaf.setContent(leafBookView.getContent());
        leaf.setVisibility(leafBookView.getVisibility());

        // 리프 수정 저장
        Long leafCheck = Optional.ofNullable(leafRepository.update(leaf))
                .orElseThrow(() -> new IllegalArgumentException("Leaf 수정 실패"));

        if (leafCheck == 1L) { // 성공 유효성 검사
            throw new IllegalArgumentException("Leaf 수정 실패");
        }

        // 도서 리프 조회
        LeafBook leafBook = Optional.ofNullable(leafBookRepository.findById(leafBookView.getLeafId()))
                .orElseThrow(() -> new IllegalArgumentException("LeafBook 조회 실패"));

        // 도서 리프 수정
        leafBook.setStartPage(leafBookView.getStartPage());
        leafBook.setEndPage(leafBookView.getEndPage());

        // 도서 리프 수정 저장
        Long leafBookCheck = Optional.ofNullable(leafBookRepository.update(leafBook))
                .orElseThrow(() -> new IllegalArgumentException("LeafBook 수정 실패"));

        if (leafBookCheck == 1L) { // 성공 유효성 검사
            throw new IllegalArgumentException("LeafBook 수정 실패");
        }

        // 도서 리프 태그 맵핑 기존 제거
        if (!leafBookView.getTags().isEmpty()) { // 태그가 있을 때만
            List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeafId(leafBook.getLeafId());

            // 리프 태그 맵핑 제거
            for (LeafTagMap leafTagMap : leafTagMaps) { // TODO: 이 부분은 더 효율적으로 수정 가능
                leafTagMapRepository.delete(leafTagMap);
            }

            // 리프 태그 맵핑 추가
            for (LeafTag tag : leafBookView.getTags()) {
                Optional.ofNullable(leafTagMapRepository.save(LeafTagMap.of(leafBook.getLeafId(), tag.getId())))
                        .orElseThrow(() -> new IllegalArgumentException("LeafTagMap 저장 실패"));
            }
        }

        return true;
    }

    @Override
    public boolean remove(Long leafId) {

        // 도서 리프 조회
        LeafBook leafBook = Optional.ofNullable(leafBookRepository.findById(leafId))
                .orElseThrow(() -> new IllegalArgumentException("LeafBook 조회 실패"));

        // 도서 리프 삭제
        leafBookRepository.delete(leafBook);

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

        return true;
    }

    @Override
    public LeafBookView get(Long leafBookId) {

        // 리프 도서 뷰 조회
        LeafBookView leafBookView = Optional.ofNullable(leafBookRepository.findLeafBookViewByLeafId(leafBookId))
                .orElseThrow(() -> new IllegalArgumentException("LeafBookView 조회 실패"));

        // 리프 태그 조회
        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeafId(leafBookId);

        // 리프 태그 조회
        List<LeafTag> tags = getLeafTags(leafTagMaps);
        leafBookView.setTags(tags);

        return leafBookView;
    }

    @Override
    public List<LeafBookView> leafBooks(Long treeId) {
        return leafBooks(treeId, SearchType.NONE, null, SortType.NONE, 1L, 10L);
    }

    @Override // 트리 기준 리프 제목 검색
    public List<LeafBookView> leafBooks(Long treeId, SearchType searchType, String search) {
        return leafBooks(treeId, searchType, search, SortType.NONE, 1L, 10L);
    }

    @Override
    public List<LeafBookView> leafBooks(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size) {

        List<LeafBookView> leafBookViews = new ArrayList<>();

        // 리프 제목 및 내용 검색
        if (searchType == SearchType.ALL) {
            leafBookViews = leafBookRepository
                    .findLeafBookViewByTreeId(treeId, SearchType.ALL, search, sortType, page, size);
        }
        // 리프 제목 검색
        else if (searchType == SearchType.TITLE) {
            leafBookViews = leafBookRepository
                    .findLeafBookViewByTreeId(treeId, SearchType.TITLE, search, sortType, page, size);
        }
        // 리프 내용 검색
        else if (searchType == SearchType.CONTENT) {
            leafBookViews = leafBookRepository
                    .findLeafBookViewByTreeId(treeId, SearchType.CONTENT, search, sortType, page, size);
        }

        // 리프 태그 조회
        for (LeafBookView leafBookView : leafBookViews) {
            List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeafId(leafBookView.getLeafId());
            List<LeafTag> tags = getLeafTags(leafTagMaps);
            leafBookView.setTags(tags);
        }

        return List.of();
    }

    @Override
    public List<LeafCardView> leafBookCards(Long bookId) {
        return leafBookCards(bookId, SearchType.NONE, null, SortType.NONE, 1L, 10L);
    }

    public List<LeafCardView> leafBookCards(Long bookId, SearchType searchType, String search, SortType sortType, Long page, Long size) {

        List<LeafCardView> leafCardViews = new ArrayList<>();

        // 리프 제목 및 내용 검색
        if (searchType == SearchType.ALL) {
            leafCardViews = leafBookRepository
                    .findLeafCardViewByBookId(bookId, SearchType.ALL, search, sortType, page, size);
        }
        // 리프 제목 검색
        else if (searchType == SearchType.TITLE) {
            leafCardViews = leafBookRepository
                    .findLeafCardViewByBookId(bookId, SearchType.TITLE, search, sortType, page, size);
        }
        // 리프 내용 검색
        else if (searchType == SearchType.CONTENT) {
            leafCardViews = leafBookRepository
                    .findLeafCardViewByBookId(bookId, SearchType.CONTENT, search, sortType, page, size);
        }

        return leafCardViews;
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
}