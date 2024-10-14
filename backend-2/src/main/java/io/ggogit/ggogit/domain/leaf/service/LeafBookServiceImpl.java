package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import io.ggogit.ggogit.domain.leaf.entity.*;
import io.ggogit.ggogit.domain.leaf.mapper.LeafImageMapper;
import io.ggogit.ggogit.domain.leaf.mapper.LeafTagMapMapper;
import io.ggogit.ggogit.domain.leaf.repository.*;
import io.ggogit.ggogit.domain.leaf.util.ImageSaveUtil;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LeafBookServiceImpl implements LeafBookService {

    private final BookRepository bookRepository;

    private final MemberRepository memberRepository;

    private final TreeRepository treeRepository;
    private final TreeBookRepository treeBookRepository;
    private final TreeSaveTmpRepository treeSaveTmpRepository;

    private final LeafRepository leafRepository;
    private final LeafImageRepository leafImageRepository;
    private final LeafTagRepository leafTagRepository;
    private final LeafTagMapRepository leafTagMapRepository;


    private final LeafImageMapper leafImageMapper;
    private final LeafTagMapMapper leafTagMapMapper;

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
                book = bookRepository.save(book);
            }
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

        leaf.setTree(parentLeaf.getTree());

        int LEAF_MAX_CHILD_COUNT = 3;
        if (LEAF_MAX_CHILD_COUNT <= parentLeaf.getChildLeafCount()) {
            throw new IllegalArgumentException("부모 Leaf의 자식 개수가 최대치 3개를 초과했습니다.");
        }

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
            LeafImage leafImage = leafImageMapper.toEntity(leaf.getId(), fileName);
            leafImageRepository.save(leafImage);
        }

        // `System`은 `LeafBook` 데이터를 생성후 저장한다.
        leafBook.setLeaf(leaf);
        leafBookRepository.save(leafBook);

        // `System`은 `LeafTagMap` 데이터를 생성후 저장한다.
        for (LeafTag leafTag : leafTags) {
            LeafTagMap leafTagMap = leafTagMapMapper.toEntity(leaf, leafTag);
            leafTagMapRepository.save(leafTagMap);
        }

        // `System`은 `TreeBook` 의 읽은 페이지를 업데이트한다.
        TreeBook treeBook = treeBookRepository.findById(leaf.getTree().getId())
                .orElseThrow(() -> new IllegalArgumentException("TreeBook 데이터가 없습니다."));

        Tree tree = treeBook.getTree();
        List<LeafBook> leafBooks = leafBookRepository.findByLeaf_Tree_Id(tree.getId());
        Long readPage = readingPage(tree.getBook().getTotalPage(), leafBooks);
        treeBook.setReadingPage(readPage);
        treeBookRepository.save(treeBook);

        return leafBook;
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

    @Override
    public LeafBook updateLeafBook(Long memberId, Long leafId, Leaf toLeaf, LeafBook leafBook, List<Long> leafTagIds) {

        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("Leaf 데이터가 없습니다."));

        // 리프 수정 권한 확인
        if (!Objects.equals(leaf.getTree().getMember().getId(), memberId)) {
            throw new IllegalArgumentException("Leaf 데이터의 MemberId와 입력받은 MemberId가 일치하지 않습니다.");
        }

        return null;
    }

    @Override
    public void deleteLeafBook(Long leafId) {

    }
}