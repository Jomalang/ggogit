package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.image.repository.ImageRepositoryImpl;
import io.ggogit.ggogit.type.UploadFolderType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeafImageServiceImpl implements LeafImageService {

    private final ImageRepositoryImpl imageRepository;

    // 임시 이미지 업로드
    @Override
    @Transactional
    public String upload(String filename, byte[] image) {
        String newFilename = imageRepository.changeFileNameToUUID(filename);
        imageRepository.saveImage(newFilename, image, UploadFolderType.TMP);
        return newFilename;
    }

    // 임시 이미지 업로드 출력
    @Override
    public byte[] print(String filename) {
        return print(filename, UploadFolderType.TMP);
    }

    @Override
    public byte[] print(String filename, UploadFolderType uploadFolderType) {
        if (imageRepository.isImageExists(filename, uploadFolderType)) {
            return imageRepository.getImageBytes(filename, uploadFolderType);
        }
        return null;
    }

    @Override
    public boolean exists(String filename, UploadFolderType uploadFolderType) {
        return imageRepository.isImageExists(filename, uploadFolderType);
    }
}