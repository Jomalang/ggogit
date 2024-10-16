package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import io.ggogit.ggogit.domain.leaf.entity.*;
import io.ggogit.ggogit.domain.leaf.repository.*;
import io.ggogit.ggogit.domain.leaf.util.ImageSaveUtil;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.tree.entity.Seed;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.entity.TreeBook;
import io.ggogit.ggogit.domain.tree.entity.TreeSaveTmp;
import io.ggogit.ggogit.domain.tree.repository.SeedRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeBookRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeSaveTmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LeafBookServiceImpl implements LeafBookService {

    private final static int LEAF_MAX_CHILD_COUNT = 3;

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    private final TreeRepository treeRepository;
    private final TreeBookRepository treeBookRepository;
    private final TreeSaveTmpRepository treeSaveTmpRepository;

    private final LeafRepository leafRepository;
    private final LeafImageRepository leafImageRepository;
    private final LeafTagRepository leafTagRepository;
    private final LeafTagMapRepository leafTagMapRepository;

    private final ImageSaveUtil imageSaveUtil;
    private final LeafBookRepository leafBookRepository;
    private final SeedRepository seedRepository;

    @Override
    public LeafBook createFirstLeafBook(Long memberId, Leaf leaf, LeafBook leafBook, List<Long> leafTagIds) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member 데이터가 없습니다."));

        // `System`은 `TreeSaveTmp`에 데이터를 조회한다.
        TreeSaveTmp treeSaveTmp = treeSaveTmpRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("TreeSaveTmp 데이터가 없습니다."));

        // `System`은 `TreeSaveTmp`에서 `Book` 데이터를 조회한다.
        Book book = treeSaveTmp.getBook();
        if (book == null) { // 직접 등록 도서 처리
            book = Book.of(treeSaveTmp, member);

            if (book.getImageFile() != null) { // 직접 등록 도서의 이미지가 있는 경우
                String filePath = book.getImageFile();
                String fileName = imageSaveUtil.extractFileName(filePath);
                String toFileName = imageSaveUtil.moveImageFile(fileName,"book", true);
                book.setImageFile(toFileName);
            }
            book = bookRepository.save(book);
        }

        Seed seed = seedRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Seed 데이터가 없습니다."));

        // `System`은 `TreeSaveTmp`에서 `Tree` 데이터를 생성후 저장한다.
        Tree tree = Tree.of(treeSaveTmp, book, member, seed);
        treeRepository.save(tree);
        leaf.setTree(tree);

        // `System`은 `TreeBook` 데이터를 생성후 저장한다.
        TreeBook treeBook = TreeBook.of(tree);
        treeBookRepository.save(treeBook);

        LeafBook savedLeafBook = createLogic(memberId, leaf, leafBook, leafTagIds);

        // `System`은 `TreeSaveTmp` 데이터를 삭제한다.
        treeSaveTmpRepository.delete(treeSaveTmp);

        return savedLeafBook;
    }

    @Override
    public LeafBook createLeafBook(Long memberId, Long parentLeafId, Leaf leaf, LeafBook leafBook, List<Long> leafTagIds) {

        // `System`은 부모 `Leaf` 데이터를 조회한다.
        Leaf parentLeaf = leafRepository.findById(parentLeafId)
                .orElseThrow(() -> new IllegalArgumentException("Leaf 부모 데이터가 없습니다."));

        if (LEAF_MAX_CHILD_COUNT <= parentLeaf.getChildLeafCount()) {
            throw new IllegalArgumentException("부모 Leaf의 자식 개수가 최대치 3개를 초과했습니다.");
        }

        leaf.setParentLeaf(parentLeaf);
        leaf.setTree(parentLeaf.getTree());

        // `System`은 `Leaf` 데이터를 저장한다.
        LeafBook savedLeafBook = createLogic(memberId, leaf, leafBook, leafTagIds);

        // `System`은 부모 `Leaf`의 자식 개수를 업데이트한다.
        parentLeaf.setChildLeafCount(parentLeaf.getChildLeafCount() + 1);
        leafRepository.save(parentLeaf);

        return savedLeafBook;
    }

    private LeafBook createLogic(Long memberId, Leaf leaf, LeafBook leafBook, List<Long> leafTagIds) {

        // `System`은 입력받은 데이터에서 `LeafTag` 데이터를 조회 후 `LeafTag` 데이터를 생성후 저장한다.
        List<LeafTag> leafTags = new ArrayList<>();
        for (Long leafTagId : leafTagIds) {

            LeafTag leafTag = leafTagRepository.findById(leafTagId)
                    .orElseThrow(() -> new IllegalArgumentException("LeafTag 데이터가 없습니다."));

            if (!Objects.equals(leafTag.getMember().getId(), memberId)) {
                throw new IllegalArgumentException("LeafTag 데이터의 MemberId와 LeafBook 데이터의 MemberId가 일치하지 않습니다.");
            }

            leafTags.add(leafTag);
        }

        // `System`은 입력받은 데이터에서 `Leaf` 컨텐츠의 이미지 이동 및 경로 변경.
        String content = leaf.getContent();
        List<String> filesNames = imageSaveUtil.extractImageFileNames(content);
        for (String fileName : filesNames) {
            imageSaveUtil.moveImageFile(fileName, "leaf"); // 이미지 파일 이동
            String changedContent = imageSaveUtil.changeTagImageSrc(content, fileName); // 태그의 이미지 경로 변경
            leaf.setContent(changedContent);
        }

        // `System`은 `Leaf` 데이터 저장
        leafRepository.save(leaf);

        // `System`은 `Leaf` 이미지를 저장한다.
        for (String fileName : filesNames) { // TODO: 이미지 파일이 없는 경우 예외 처리
            LeafImage leafImage = LeafImage.of(leaf.getId(), fileName);
            leafImageRepository.save(leafImage);
        }

        // `System`은 `LeafBook` 데이터를 생성후 저장한다.
        leafBook.setLeaf(leaf);
        leafBookRepository.save(leafBook);

        // `System`은 `LeafTagMap` 데이터를 생성후 저장한다.
        for (LeafTag leafTag : leafTags) {
            LeafTagMap leafTagMap = LeafTagMap.of(leaf, leafTag);
            leafTagMapRepository.save(leafTagMap);
        }

        // `System`은 `TreeBook` 의 읽은 페이지를 업데이트한다.
        TreeBook treeBook = treeBookRepository.findById(leaf.getTree().getId())
                .orElseThrow(() -> new IllegalArgumentException("TreeBook 데이터가 없습니다."));

        Tree tree = leaf.getTree();
        List<LeafBook> leafBooks = new ArrayList<>();
        leafRepository.findByTree(tree).forEach(lf -> {
            LeafBook lb = leafBookRepository.findByLeaf(lf)
                    .orElseThrow(() -> new IllegalArgumentException("LeafBook 데이터가 없습니다."));
            leafBooks.add(lb);
        });

        Long readPage = readingPage(tree.getBook().getTotalPage(), leafBooks);
        treeBook.setReadingPage(readPage);
        treeBookRepository.save(treeBook);

        return leafBook;
    }

    @Override
    @Transactional
    public LeafBook updateLeafBook(Long memberId, Long leafId, Leaf toLeaf, LeafBook toLeafBook, List<Long> toLeafTagIds) {

        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("Leaf 데이터가 없습니다."));

        // 리프 수정
        leaf.setTitle(toLeaf.getTitle());
        leaf.setContent(toLeaf.getContent()); // TODO: 이미지 저장 및 경로 변경
        leaf.setVisibility(toLeaf.getVisibility());
        leafRepository.save(leaf);

        LeafBook leafBook = leafBookRepository.findByLeaf(leaf)
                .orElseThrow(() -> new IllegalArgumentException("LeafBook 데이터가 없습니다."));

        // 리프 도서 수정
        leafBook.setStartPage(toLeafBook.getStartPage());
        leafBook.setEndPage(toLeafBook.getEndPage());
        leafBookRepository.save(leafBook);

        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(leaf);
        for (LeafTagMap leafTagMap : leafTagMaps) {
            leafTagMap.setActive(false);
            leafTagMapRepository.save(leafTagMap);
        }

        for (Long leafTagId : toLeafTagIds) {
            LeafTag leafTag = leafTagRepository.findById(leafTagId)
                    .orElseThrow(() -> new IllegalArgumentException("LeafTag 데이터가 없습니다."));

            if (!Objects.equals(leafTag.getMember().getId(), memberId)) {
                throw new IllegalArgumentException("LeafTag 데이터의 권한이 없습니다.");
            }

            // 리프 태그 맵핑 수정
            LeafTagMapId leafTagMapId = LeafTagMapId.of(leaf.getId(), leafTag.getId());
            leafTagMapRepository.findById(leafTagMapId).ifPresentOrElse(
                leafTagMap -> { // 이미 있는 경우 활성화
                    leafTagMap.setActive(true);
                    leafTagMapRepository.save(leafTagMap);
                },
                () -> { // 없는 경우 생성
                    LeafTagMap leafTagMap = LeafTagMap.of(leaf, leafTag);
                    leafTagMapRepository.save(leafTagMap);
                }
            );

        }

        return leafBook;
    }

    @Override
    public void deleteLeafBook(Long leafId) {

        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("Leaf 데이터가 없습니다."));

        // LeafTagMap 비활성화
        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(leaf);
        for (LeafTagMap leafTagMap : leafTagMaps) {
            leafTagMap.setActive(false);
            leafTagMapRepository.save(leafTagMap);
        }

        // LeafImage 삭제
        leafImageRepository.findById(leafId)
                .ifPresent(leafImageRepository::delete);

        // LeafBook 삭제
        leafBookRepository.findByLeaf(leaf)
                .ifPresent(leafBookRepository::delete);

        // Leaf 삭제
        leafRepository.delete(leaf);
    }

    @Override
    public boolean isOwner(Long memberId, Long leafId) {

        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("Leaf 데이터가 없습니다."));

        return Objects.equals(leaf.getTree().getMember().getId(), memberId);
    }

    private long readingPage(int totalPage, List<LeafBook> leafBooks) {
        boolean[] isRead = new boolean[totalPage + 1]; // 읽은 페이지 체크
        long readPage = 0L; // 읽은 페이지 수
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
}