package io.ggogit.ggogit.api.book;

import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@SpringBootTest
@AutoConfigureMockMvc
class AladinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("테스트 이름")
    void search() throws Exception {
        // given

        // when
        mockMvc.perform(post("/aladin")
                .param("query", "자바 프로그래밍"))
                .andExpect(status().isCreated());
        // then
        List<Book> book = bookRepository.findByMember_Id(1L);
        for (Book b : book) {
            System.out.println(b.getTitle());
        }
    }
}