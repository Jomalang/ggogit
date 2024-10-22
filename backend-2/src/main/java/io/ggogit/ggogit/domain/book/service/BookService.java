package io.ggogit.ggogit.domain.book.service;


import io.ggogit.ggogit.api.book.dto.BookInfoResponse;
import io.ggogit.ggogit.domain.book.entity.Book;
import org.springframework.web.multipart.MultipartFile;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    /**
     * 책 등록
     * @param book
     * @return bookId
     */
//    Long register(Book book);

    /**
     * 책 상세 조회
     * @param bookId
     * @return
     */
//    BookDetailView get(Long bookId);

    /**
     * 책 목록 조회
     * @return
     */
    // TODO: 페이징 기능, 검색 기능 추가
    //public List<Book> getBooks(int page, String query, String sort);

    /**
     *
     * @param page 페이지 수
     * @param query 검색어
     * @param filter 검색 조건(제목, 저자, 출판사)
     * @return
     */
    //목록 조회 + 페이징, 정렬, 검색 기능
    List<Book> getBooks(int page, String query, String filter);

    int saveAll(List<Book> books);

    Book modify(Long bookId, Book entity, MultipartFile imageFile);

    boolean isOwner(Long bookId, Long memberId);

    Book findById(Long bookId);

    /**
     * 책 수정
     * @param book
     */
//    void modify(Book book);

    /**
     * 책 삭제
     * @param bookId
     */
//    void remove(Long bookId);

    /**
     * 타이틀로 도서 검색
     * @param title
     * @return
     */
//    List<BookPreviewView> getBooksbyTitle(String title);

    /**
     * 저자로 도서 검색
     * @param author
     * @return
     */
//    List<BookPreviewView> getBooksbyAuthor(String author);

    /**
     * 저자로 도서 검색
     * @param author
     * @return
     */
//    BookInfoView getBookbyId(Long bookId);

    BookCategory getBookCategory(Long bookId);

    BookInfoResponse getBookbyId(Long id);
}
