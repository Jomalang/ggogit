package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.type.UploadFolderType;
import org.springframework.web.multipart.MultipartFile;

public interface LeafImageService {

    // 이미지 업로드
    String upload(String filename, byte[] image);

    // 이미지 출력
    byte[] print(String filename);

    byte[] print(String filename, UploadFolderType uploadFolderType);
}