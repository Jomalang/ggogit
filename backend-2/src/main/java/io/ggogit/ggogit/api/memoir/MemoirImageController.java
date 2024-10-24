package io.ggogit.ggogit.api.memoir;

import io.ggogit.ggogit.domain.image.repository.ImageRepositoryImpl;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("memoir-image")
@RequiredArgsConstructor
public class MemoirImageController {

    private final ImageRepositoryImpl imageRepository;

    @PostMapping("upload-tmp")
    public String uploadImageToTemp(@RequestParam final MultipartFile image) throws IOException {
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
    @GetMapping(value = "render-tmp", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] printEditorImage(@RequestParam final String filename) {
        return imageRepository.getImageBytes(filename, UploadFolderType.TMP);
    }
}
