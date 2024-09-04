package Recorders.ggogit.domain.book.service;

import Recorders.ggogit.domain.book.entity.Book;
import Recorders.ggogit.domain.book.view.BookDetailView;

import java.util.List;

public interface BookService {

    /**
     * 책 등록
     * @param book
     * @return bookId
     */
    Long register(Book book);

    /**
     * 책 상세 조회
     * @param bookId
     * @return
     */
    BookDetailView get(Long bookId);

    /**
     * 책 목록 조회
     * @return
     */
    List<Book> gets(); // TODO: 페이징 기능, 검색 기능 추가

    /**
     * 책 수정
     * @param book
     */
    void modify(Book book);

    /**
     * 책 삭제
     * @param bookId
     */
    void remove(Long bookId);
}
