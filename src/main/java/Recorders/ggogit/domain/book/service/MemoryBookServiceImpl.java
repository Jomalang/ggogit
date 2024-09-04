package Recorders.ggogit.domain.book.service;

import Recorders.ggogit.domain.book.entity.Book;
import Recorders.ggogit.domain.book.view.BookDetailView;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemoryBookServiceImpl implements BookService {

    @Override
    public Long register(Book book) {
        return 0L;
    }

    @Override
    public BookDetailView get(Long bookId) {
        return BookDetailView.builder()
                .imageFileName("test.jpg")
                .bookCategoryName("재테크")
                .title("돈의 속성")
                .author("김승호")
                .publisher("스노우폭스북스")
                .publicDate(LocalDateTime.of(2024, 7, 10, 0, 0))
                .totalPage(336L)
                .build();
    }

    @Override
    public List<Book> gets() {
        return List.of();
    }

    @Override
    public void modify(Book book) {

    }

    @Override

    public void remove(Long bookId) {

    }
}