package io.ggogit.ggogit.domain.image;

import io.ggogit.ggogit.type.UploadFolderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ImageRepositoryTest {

    @TempDir
    Path tempDir;

    @InjectMocks
    private ImageRepository imageRepository;

    @Mock
    private UploadFolderType folderType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        imageRepository = new ImageRepository(tempDir.toString());
    }

//    @Test
//    @DisplayName("이미지 존재 확인")
//    void isImageExists() throws IOException {
//        // given
//        String imageName = "testImage.jpg";
//        Path imagePath = tempDir.resolve("tree").resolve(imageName);
//        Files.createDirectories(imagePath.getParent());
//        Files.createFile(imagePath);
//
//        // when
//        when(folderType.getFolderName()).thenReturn("tree");
//
//        // then
//        assertTrue(imageRepository.isImageExists(imageName, folderType));
//    }
//
//    @Test
//    @DisplayName("이미지 저장 성공")
//    void saveImage() throws IOException {
//        // given
//        String imageName = "testImage.jpg";
//        byte[] imageData = "test data".getBytes();
//
//        when(folderType.getFolderName()).thenReturn("tree");
//
//        // when
//        imageRepository.saveImage(imageName, imageData, folderType);
//
//        // then
//        Path imagePath = tempDir.resolve("tree").resolve(imageName);
//        assertTrue(Files.exists(imagePath));
//        assertArrayEquals(imageData, Files.readAllBytes(imagePath));
//    }
//
//    @Test
//    @DisplayName("이미지 이동 성공")
//    void moveImage() throws IOException {
//        // given
//        String imageName = "testImage.jpg";
//        Path sourcePath = tempDir.resolve("source").resolve(imageName);
//        Path targetPath = tempDir.resolve("target").resolve(imageName);
//        Files.createDirectories(sourcePath.getParent());
//        Files.createFile(sourcePath);
//
//        when(folderType.getFolderName()).thenReturn("source", "target");
//
//        // when
//        imageRepository.moveImage(imageName, folderType, folderType);
//
//        // then
//        assertFalse(Files.exists(sourcePath));
//        assertTrue(Files.exists(targetPath));
//    }
//
//    @Test
//    @DisplayName("이미지 삭제 성공")
//    void deleteImage() throws IOException {
//        // given
//        String imageName = "testImage.jpg";
//        Path imagePath = tempDir.resolve("tree").resolve(imageName);
//        Files.createDirectories(imagePath.getParent());
//        Files.createFile(imagePath);
//
//        when(folderType.getFolderName()).thenReturn("tree");
//
//        // when
//        imageRepository.deleteImage(imageName, folderType);
//
//        // then
//        assertFalse(Files.exists(imagePath));
//    }
//
//    @Test
//    @DisplayName("파일 이름을 UUID로 변경")
//    void changeFileNameToUUID() {
//        // given
//        String originalFileName = "example.jpg";
//        String expectedExtension = "jpg";
//
//        // when
//        String newFileName = imageRepository.changeFileNameToUUID(originalFileName);
//
//        // then
//        assertNotNull(newFileName);
//        assertTrue(newFileName.matches("^[a-f0-9]{32}\\." + expectedExtension + "$"));
//    }
}