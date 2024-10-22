package io.ggogit.ggogit.domain.book.service;

import io.ggogit.ggogit.api.book.dto.BookInfoResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.book.repository.BookCategoryRepository;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
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


    //목록 조회 + 페이징, 정렬, 검색 기능
    @Override
    public List<Book> getBooks(int page, String query, String filter) {
        int size = 10;
        int offset = (page - 1) * size;

        //TODO: 정렬기준 추가
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(offset, size, sort);

        if (query == null) {
            return bookRepository.findAll(pageable).getContent();
        } else{
            switch (filter) {
                case "title":
                    return bookRepository.findByTitle(query, pageable);
                case "author":
                    return bookRepository.findByAuthor(query, pageable);
                case "publisher":
                    return bookRepository.findByPublisher(query, pageable);
                default:
                    return bookRepository.findByTitle(query, pageable);
            }
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
}
