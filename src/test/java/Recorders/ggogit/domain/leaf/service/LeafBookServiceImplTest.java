package Recorders.ggogit.domain.leaf.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class LeafBookServiceImplTest {

    @Autowired
    private LeafBookServiceImpl leafBookService;

    @Test
    void extractImageFileNames() {
        // Given
        String content = "<p><img src=\"/api/v1/leaf/image-print?filename=f34a1cd4a5c2447681d40f6572be2a4a.jpg\" alt=\"image alt attribute\" contenteditable=\"false\">sdfasdfsdfasdfasdf</p><p>asdfasd</p><p>fasd</p><p>fasdf</p><p>asdf</p><p>asd</p><p>fas</p><p>dfasdfasdfasdf</p><p>asdfasd</p><p>fas</p><p>dfa</p><img src=\"/api/v1/leaf/image-print?filename=f34a1cd4a5c2447681d40f6572be2a4a.jpg\" alt=\"image alt attribute\" contenteditable=\"false\"><p>sdf</p><p>asd</p><p>fas</p><p>df</p><img src=\"/api/v1/leaf/image-print?filename=f34a1cd4a5c2447681d40f6572be2a4a.jpg\" alt=\"image alt attribute\" contenteditable=\"false\"><p>asdfsdf</p>";
        // When
        List<String> names = leafBookService.extractImageFileNames(content);

        // Then
        for (String name : names) {
            System.out.println(name);
        }
    }
}