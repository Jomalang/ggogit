package io.ggogit.ggogit.api.leaf;

import io.ggogit.ggogit.domain.leaf.service.LeafImageService;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class LeafImageController {

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
            value = "/leaf/image/{filename}",
            produces = {
                    MediaType.IMAGE_GIF_VALUE,
                    MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_PNG_VALUE
            }
    )
    public byte[] print(
            @PathVariable final String filename
    ) {
        if (leafImageService.exists(filename, UploadFolderType.LEAF)) {
            return leafImageService.print(filename, UploadFolderType.LEAF);
        }
        return leafImageService.print(filename, UploadFolderType.TMP);
    }
}