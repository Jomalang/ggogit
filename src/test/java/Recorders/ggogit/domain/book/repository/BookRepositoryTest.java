package Recorders.ggogit.domain.book.repository;

import Recorders.ggogit.domain.book.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("도서 저장 테스트")
    void save() {
        // given 주어진것
        Book book = Book.builder()
                .memberId(1L) // <
                .totalPage(100)
                .title("테스트123")
                .bookCategoryId(1L)
                .author("테스트")
                .isbn("123123132")
                .publisher("테스트")
                .imageFile("테스트")
//                .publicDate(LocalDateTime.of(2021, 1, 1, 0, 0))
                .updateTime(null)
                .createTime(null)
                .build();

        // when 실행했을때
        bookRepository.save(book);

        // then 결과
        Book savedBook = bookRepository.findByTitle("테스트123");
        assertThat(savedBook.getMemberId()).isEqualTo(book.getMemberId());
        assertThat(savedBook.getTotalPage()).isEqualTo(book.getTotalPage());
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(savedBook.getPublisher()).isEqualTo(book.getPublisher());
        assertThat(savedBook.getImageFile()).isEqualTo(book.getImageFile());
//        assertThat(savedBook.getPublicDate()).isEqualTo(book.getPublicDate());
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }

    @DisplayName("테스트 코드 작동")
    @Test
    public void testExample() {
        String actual = "Hello, World!";
        assertThat(actual).isEqualTo("Hello, World!");

    }
}