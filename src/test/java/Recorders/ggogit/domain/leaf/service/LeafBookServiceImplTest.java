package Recorders.ggogit.domain.leaf.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class LeafBookServiceImplTest {

    @Autowired
    private LeafBookServiceImpl leafBookService;

    @Test
    void moveImageFile() {
        // Given
        String sourcePath = "C:\\Users\\gksxo\\Desktop\\github\\ggogit\\src\\main\\webapp\\image\\tmp\\test.jpg";
        String targetPath = "image" + File.separator + "book" + File.separator + "test.jpg";
        // When
        leafBookService.moveImageFile(sourcePath, targetPath);

        // Then
    }
}