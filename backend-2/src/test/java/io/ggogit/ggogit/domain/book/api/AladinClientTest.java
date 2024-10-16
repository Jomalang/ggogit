package io.ggogit.ggogit.domain.book.api;

import io.ggogit.ggogit.domain.book.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AladinClientTest {

    @Autowired
    private AladinClient aladinClient;

    @Test
    @DisplayName("알라딘 API를 통해 책 정보를 가져온다.")
    void fetchBooks() {
        // given
        String query = "자바 프로그래밍";

        // when
        List<Book> books = aladinClient.fetchBooks(query);
        for (Book book : books) {
            System.out.println(book.getIsbn() + " : " + book.getTotalPage());
        }
        // then
    }
}