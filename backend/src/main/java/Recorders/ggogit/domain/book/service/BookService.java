package Recorders.ggogit.domain.book.service;

import Recorders.ggogit.domain.book.entity.Book;
import Recorders.ggogit.domain.book.view.BookInfoView;
import Recorders.ggogit.domain.book.view.BookPreviewView;
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

    /**
     * 타이틀로 도서 검색
     * @param title
     * @return
     */
    List<BookPreviewView> getBooksbyTitle(String title);

    /**
     * 저자로 도서 검색
     * @param author
     * @return
     */
    List<BookPreviewView> getBooksbyAuthor(String author);

    /**
     * 저자로 도서 검색
     * @param author
     * @return
     */
    BookInfoView getBookbyId(Long bookId);
}
