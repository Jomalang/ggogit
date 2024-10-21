package io.ggogit.ggogit.domain.book.service;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

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
}
