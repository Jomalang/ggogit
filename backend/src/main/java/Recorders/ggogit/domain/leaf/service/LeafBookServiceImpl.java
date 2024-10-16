package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.book.entity.Book;
import Recorders.ggogit.domain.book.repository.BookRepository;
import Recorders.ggogit.domain.leaf.entity.*;
import Recorders.ggogit.domain.leaf.mapper.LeafMapper;
import Recorders.ggogit.domain.leaf.repository.*;
import Recorders.ggogit.domain.leaf.utill.ImageSaveUtill;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import Recorders.ggogit.domain.tree.entity.Tree;
import Recorders.ggogit.domain.tree.entity.TreeBook;
import Recorders.ggogit.domain.tree.entity.TreeImage;
import Recorders.ggogit.domain.tree.entity.TreeSaveTmp;
import Recorders.ggogit.domain.tree.repository.TreeBookRepository;
import Recorders.ggogit.domain.tree.repository.TreeImageRepository;
import Recorders.ggogit.domain.tree.repository.TreeRepository;
import Recorders.ggogit.domain.tree.repository.TreeSaveTmpRepository;
import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.type.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeafBookServiceImpl implements LeafBookService {

    private final static int LEAF_MAX_CHILD_COUNT = 3;

    private final LeafRepository leafRepository;
    private final LeafTagRepository leafTagRepository;
    private final LeafBookRepository leafBookRepository;
    private final LeafImageRepository leafImageRepository;
    private final LeafTagMapRepository leafTagMapRepository;
    private final BookRepository bookRepository;
    private final TreeRepository treeRepository;
    private final TreeSaveTmpRepository treeSaveTmpRepository;
    private final TreeImageRepository treeImageRepository;

    private final LeafMapper leafMapper;
    private final ImageSaveUtill imageSaveUtill;
    private final TreeBookRepository treeBookRepository;

    @Override
    @Transactional
    public LeafBookView register(LeafBookView leafBookView, Long seedId, Long memberId) {
        if (leafBookView.getParentLeafId() == null && leafBookView.getTreeId() == null) {
            return registerRoot(leafBookView, seedId, memberId);
        } else {
            return registerNode(leafBookView, seedId, memberId);
        }
    }

    @Override
    @Transactional
    public LeafBookView registerRoot(LeafBookView leafBookView, Long seedId, Long memberId) {
        assert leafBookView.getParentLeafId() == null; // 부모 리프가 없어야 함

        // TMP트리 존재 확인
        TreeSaveTmp treeSaveTmp = Optional.ofNullable(treeSaveTmpRepository.findById(memberId))
                .orElseThrow(() -> new IllegalArgumentException("TMP Tree가 존재하지 않습니다."));

        // 트리 생성 로직
        Tree tree = treeSaveTmp.toTree();

        if (seedId == 1) { // 도서 트리인 경우 도서 생성
            Long bookId = treeSaveTmp.getBookId();

            Book book; // 도서 조회
            if (bookId != null) {
                book = Optional.ofNullable(bookRepository.findById(bookId))
                        .orElseThrow(() -> new IllegalArgumentException("Book 조회 실패"));

            }
            else { // 도서 없는 경우 생성
                book = treeSaveTmp.toBook();
                bookRepository.save(book);
                treeSaveTmp.setBookId(book.getId());

                if (treeSaveTmp.hasImage()) { // 이미지가 있는 경우 저장
                    String fileName = imageSaveUtill.extractFileName(treeSaveTmp.getFilePath());
                    String toFileName = imageSaveUtill.moveImageFile(fileName,"book", true);
                    book.setImageFile(toFileName);
                    bookRepository.update(book);
                }
            }

            tree.setBookId(book.getId());
        }

        // 트리 저장 로직
        treeRepository.save(tree);
        leafBookView.setTreeId(tree.getId());

        // 트리 이미지 저장 로직
        if (seedId != 1) {
            String toFileName = imageSaveUtill.moveImageFile(treeSaveTmp.getImageFile(),"book", true);
            TreeImage treeImage = TreeImage.builder()
                    .name(toFileName)
                    .treeId(tree.getId())
                    .build();
            treeImageRepository.save(treeImage);
        }

        // 트리 북 저장 로직
        TreeBook treeBook = TreeBook.builder()
                .treeId(tree.getId())
                .readingPage(0)
                .build();
        treeBookRepository.save(treeBook);

        // 트리 저장 TMP 삭제
        treeSaveTmpRepository.deleteByMemberId(memberId);

        return registerLogic(leafBookView);
    }

    @Override
    @Transactional
    public LeafBookView registerNode(LeafBookView leafBookView, Long seedId, Long memberId) {

        // 부모 리프 조회
        Leaf parentLeaf = Optional.ofNullable(leafRepository.findById(leafBookView.getParentLeafId()))
                .orElseThrow(() -> new IllegalArgumentException("부모 Leaf 조회 실패"));

        // 트리 아이디 저장
        leafBookView.setTreeId(parentLeaf.getTreeId());

        // 부모 자식 개수 확인
        if (LEAF_MAX_CHILD_COUNT <= parentLeaf.getChildLeafCount()) {
            throw new IllegalArgumentException("부모 Leaf의 자식 개수가 최대치 3개를 초과했습니다.");
        }

        // 도서 리프 저장 로직
        registerLogic(leafBookView);

        // 자식 수 증가
        parentLeaf.setChildLeafCount(parentLeaf.getChildLeafCount() + 1);

        // 부모 리프 저장
        Long parentLeafCheck = leafRepository.update(parentLeaf);
        if (parentLeafCheck == null || parentLeafCheck != 1) {
            throw new IllegalArgumentException("부모 Leaf 수정 실패");
        }

        return leafBookView;
    }

    private LeafBookView registerLogic(LeafBookView leafBookView) {
        Long treeId = leafBookView.getTreeId();
        String content = leafBookView.getContent();

        // 태그 존재 확인
        leafBookView.getTags().forEach(tag -> {
            Long tagId = tag.getId();
            if (!leafTagRepository.existsById(tagId)) {
                throw new IllegalArgumentException("태그가 존재하지 않습니다.");
            }
        });

        // 이미지 저장 로직
        List<String> filesNames = imageSaveUtill.extractImageFileNames(content);
        for (String fileName : filesNames) {
            imageSaveUtill.moveImageFile(fileName, "leaf"); // 이미지 파일 이동
            String changedContent = imageSaveUtill.changeTagImageSrc(content, fileName); // 태그의 이미지 경로 변경
            leafBookView.setContent(changedContent);
        }

        // 리프 저장
        Leaf leaf = leafBookView.toLeaf();
        Long leafSaveCheck = leafRepository.save(leaf);
        if (leafSaveCheck == null || leafSaveCheck != 1) {
            throw new IllegalArgumentException("Leaf 저장 실패");
        }

        // 이미지 DB 저장
        for (String fileName : filesNames) {
            LeafImage leafImage = leafMapper.toEntity(leaf.getId(), fileName);
            leafImageRepository.save(leafImage);
        }

        leafBookView.setLeafId(leaf.getId());
        LeafBook leafBook = leafBookView.toLeafBook();
        Long bookSaveCheck = leafBookRepository.save(leafBook);
        if (bookSaveCheck == null || bookSaveCheck != 1) {
            throw new IllegalArgumentException("LeafBook 저장 실패");
        }

        // 리프 태그 맵핑 저장
        leafBookView.getTags().forEach(tag -> {
            Optional.ofNullable(leafTagMapRepository.save(LeafTagMap.of(leaf.getId(), tag.getId())))
                    .orElseThrow(() -> new IllegalArgumentException("LeafTagMap 저장 실패"));
        });

        // 도서 리프 읽은 페이지 수정
        Tree tree = treeRepository.findById(treeId);
        TreeBook treeBook = treeBookRepository.findById(treeId);

        Book book = bookRepository.findById(tree.getBookId());
        List<LeafBook> leafBooks = leafBookRepository.findByTreeId(treeId);
        int readingPage = readingPage(book.getTotalPage(), leafBooks);
        treeBook.setReadingPage(readingPage);

        // 도서 리프 읽은 페이지 저장
        treeBookRepository.update(treeBook);

        // 결과 확인
        return leafBookView;
    }

    private int readingPage(int totalPage, List<LeafBook> leafBooks) {
        boolean[] isRead = new boolean[totalPage]; // 읽은 페이지 체크
        int readPage = 0; // 읽은 페이지 수
        for (LeafBook book : leafBooks) {
            for (int i = book.getStartPage(); i <= book.getEndPage(); i++) {
                if (!isRead[i]) { // 읽지 않은 페이지만
                    isRead[i] = true;
                    readPage++;
                }
            }
        }
        return readPage;
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
    public LeafBookView getLeafBookView(Long leafBookId) {

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
    public List<LeafBookView> getLeafBookViews(Long treeId) {
        return getLeafBookViews(treeId, SearchType.NONE, null, SortType.NONE, 1L, 10L);
    }

    @Override // 트리 기준 리프 제목 검색
    public List<LeafBookView> getLeafBookViews(Long treeId, SearchType searchType, String search) {
        return getLeafBookViews(treeId, searchType, search, SortType.NONE, 1L, 10L);
    }

    @Override
    public List<LeafBookView> getLeafBookViews(Long treeId, SearchType searchType, String search, SortType sortType, Long page, Long size) {

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

        return leafBookViews;
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