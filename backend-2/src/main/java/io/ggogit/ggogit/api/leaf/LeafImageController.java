package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.domain.leaf.service.LeafImageService;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class LeafImageController {

    @Value("${file.tmp-dir}")
    private String tmpDir;

    private final LeafImageService leafImageService;

    @PostMapping("/leaf/image-upload")
    public String upload(
            @RequestParam final MultipartFile image
    ) throws IOException {

        if (image.isEmpty()) {
            throw new RuntimeException("이미지가 없습니다.");
        }

        String filename = image.getOriginalFilename(); //원본 이미지명
        byte[] imageBytes = image.getBytes(); //이미지 byte[]

        return leafImageService.upload(filename, imageBytes);
    }

    @GetMapping(
            value = "/leaf/image-print",
            produces = {
                    MediaType.IMAGE_GIF_VALUE,
                    MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_PNG_VALUE
            }
    )
    public byte[] print(
            @RequestParam final String filename
    ) {
        return leafImageService.print(filename, UploadFolderType.TMP);
    }

    @GetMapping(
            value = "/saved/leaf/image-print",
            produces = {
                    MediaType.IMAGE_GIF_VALUE,
                    MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_PNG_VALUE
            }
    )
    public byte[] savedPrint(
            @RequestParam final String filename
    ) {
        return leafImageService.print(filename, UploadFolderType.LEAF);
    }
}