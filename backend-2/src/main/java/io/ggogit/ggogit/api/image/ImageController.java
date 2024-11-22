package io.ggogit.ggogit.api.image;

import io.ggogit.ggogit.domain.image.service.ImageService;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{domain}/{fileName}")
    public byte[] getImage(
            @PathVariable(name = "domain") String domain,
            @PathVariable(name = "fileName") String fileName) {
        if(domain == null || domain.isEmpty()) {
            throw new IllegalArgumentException("이미지 이름이 없습니다.");
        }
        if(domain.equals("tree")) {
            return imageService.getImageByte(fileName, UploadFolderType.TREE);
        }
        if(domain.equals("book")) {
            return imageService.getImageByte(fileName, UploadFolderType.BOOK);
        }
        if(domain.equals("memoir")) {
            return imageService.getImageByte(fileName, UploadFolderType.MEMOIR);
        }
        if(domain.equals("leaf")) {
            return imageService.getImageByte(fileName, UploadFolderType.LEAF);
        }
        if(domain.equals("member")) {
            return imageService.getImageByte(fileName, UploadFolderType.MEMBER);
        }
        if(domain.equals("memberBackground")) {
            return imageService.getImageByte(fileName, UploadFolderType.MEMBER_BACKGROUND);
        }
        else{
            throw new IllegalArgumentException("이미지 폴더 타입이 잘못되었습니다.");
        }
    }
}
