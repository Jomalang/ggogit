package io.ggogit.ggogit.domain.book.service;

import io.ggogit.ggogit.api.book.dto.BookInfoResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.book.repository.BookCategoryRepository;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;
    private final MemberRepository memberRepository;
    private final TreeRepository treeRepository;


    //목록 조회 + 페이징, 정렬, 검색 기능
    @Override
    public Page<Book> getBooks(int page, String query, String filter) {
        int limit = 10;
        int offset = (page - 1);

        //TODO: 정렬기준 추가
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(offset, limit, sort);

        if (query == null) {
            return bookRepository.findAll(pageable);
        } else{
            return bookRepository.findByFilter(filter, query, pageable);
        }
    }

    @Override
    public int saveAll(List<Book> books) {
        List<Book> savedBooks = bookRepository.saveAll(books);
        return savedBooks.size();
    }

    @Override
    public Book modify(Long bookId, Book tobook, @Nullable MultipartFile imageFile) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다."));

        if (imageFile != null) {
            // TODO: 이미지 파일 업로드 로직 추가
            // 이미지 파일 업로드
            // 이미지 파일 경로 저장
            // book.setImageFile(imageFile);
        }

        book.setImageFile(tobook.getImageFile());
        book.setTitle(tobook.getTitle());
        book.setAuthor(tobook.getAuthor());
        book.setPublisher(tobook.getPublisher());
        book.setTotalPage(tobook.getTotalPage());

        return bookRepository.save(book);
    }

    @Override
    public boolean isOwner(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다."));
        return book.getMember().getId().equals(memberId);
    }

    @Override
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다."));
    }


    @Override
    public BookCategory getBookCategory(Long bookId) {
        return bookCategoryRepository.findById(bookId).orElse(null);
    }

    @Override
    public BookInfoResponse getBookbyId(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null)
            return BookInfoResponse.of(book);
        return null;
    }

    @Override
    public boolean apiCheck(Long bookId) {

        Member member = memberRepository.findById(999L)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return bookRepository.existsByIdAndMember(bookId, member);
    }

    @Override
    public Book findByTreeId(Long treeId) {

        Tree tree = treeRepository.findById(treeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다."));
        Book book = tree.getBook();
        return book;
    }
}
