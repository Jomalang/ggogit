package io.ggogit.ggogit.api.leaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class LeafImageController {

    @Value("${file.tmp-dir}")
    private String tmpDir;

    @PostMapping("/leaf/image-upload")
    public String uploadEditorImage(
            @RequestParam final MultipartFile image
    ) {
        if (image.isEmpty()) {
            throw new RuntimeException("이미지가 없습니다.");
        }

        String filename = image.getOriginalFilename(); //원본 이미지명
        String saveFilename = changeFileNameToUUID(filename);
        Path fileFullPath = Paths.get(tmpDir, saveFilename); //  경로 획득

        if (!Files.exists(fileFullPath.getParent())) { // 폴더 경로 존재 확인
            try {
                Files.createDirectories(fileFullPath.getParent());
            } catch (IOException e) {
                throw new RuntimeException("폴더 생성 실패", e);
            }
        }

        try {
            image.transferTo(fileFullPath);
            log.info("saveFilename = {}", saveFilename);
            return saveFilename; //업로드된 파일명 반환

        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }
    }

    @GetMapping(
            value = "/leaf/image-print",
            produces = {
                    MediaType.IMAGE_GIF_VALUE,
                    MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_PNG_VALUE
            }
    )
    public byte[] printEditorImage(
            @RequestParam final String filename
    ) {

        Path fileFullPath = Paths.get(tmpDir, filename); //업로드된 파일의 전체 경로 획득

        if (!fileFullPath.toFile().exists()) { //파일이 없는 경우 예외 처리
            throw new RuntimeException("파일이 존재하지 않습니다.");
        }

        try {
            return Files.readAllBytes(fileFullPath); //이미지 파일을 byte[]로 변환 후 반환
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 실패", e);
        }
    }


    private String changeFileNameToUUID(String fileName) {
        String extension = getFileExtension(fileName);
        return generateUUIDWithoutHyphens() + "." + extension;
    }

    private static String generateUUIDWithoutHyphens() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    private static String getFileExtension(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName is null");
        }

        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // 확장자가 없는 경우
        }

        return fileName.substring(lastIndexOf + 1);
    }
}