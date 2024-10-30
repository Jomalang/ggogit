package io.ggogit.ggogit.api.memoir;

import io.ggogit.ggogit.domain.image.repository.ImageRepositoryImpl;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("memoir-image")
@RequiredArgsConstructor
@Slf4j
public class MemoirImageController {

    private final ImageRepositoryImpl imageRepository;

    @PostMapping("upload-tmp")
    public String uploadImageToTemp(@RequestParam final MultipartFile image) {
        if(image.isEmpty()) return "";

        //이미지 파일명 변경
        String saveName = imageRepository.changeFileNameToUUID(image.getOriginalFilename());

        try{
            //tmp폴더에 이미지 저장
            imageRepository.saveImage(saveName, image.getBytes(), UploadFolderType.TMP);
        } catch (IOException e) {
            //TODO 예외처리
            throw new RuntimeException(e);
        }
        //저장된 이미지 파일명 반환
        return saveName;
    }
    @GetMapping(value = "return-byte", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] printEditorImage(@RequestParam final String filePath) {
        if(imageRepository.isImageExists(filePath, UploadFolderType.TMP)) {
            return imageRepository.getImageBytes(filePath, UploadFolderType.TMP);
        } else{
            return imageRepository.getImageBytes(filePath, UploadFolderType.MEMOIR);
        }
    }

    // 이미지 파일명을 받아서 이미지의 전체 경로를 반환
    @Deprecated
    @GetMapping(value = {"path-tmp", "path-memoir"})
    public ResponseEntity<String> getImageFullPath(@RequestParam final String fileName) {
        Path path = null;
        if(imageRepository.isImageExists(fileName, UploadFolderType.TMP)) {
            path = imageRepository.getImageFullPath(fileName, UploadFolderType.TMP);
            return new ResponseEntity<>(path.toString(), HttpStatus.OK);
        } else{
            path = imageRepository.getImageFullPath(fileName, UploadFolderType.MEMOIR);
            return new ResponseEntity<>(path.toString(), HttpStatus.OK);
        }
    }

    @GetMapping("move")
    public void moveImage(@RequestParam final String[] filename) {
        for (String fileName : filename) {
            imageRepository.moveImage(fileName, UploadFolderType.TMP, UploadFolderType.MEMOIR);
        }
    }
}
