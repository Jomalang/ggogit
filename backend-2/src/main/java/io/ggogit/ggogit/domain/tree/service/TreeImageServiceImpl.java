package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.image.repository.ImageRepositoryImpl;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TreeImageServiceImpl implements TreeImageService {

    private final ImageRepositoryImpl imageRepository;

    @Override
    public String upload(String filename, byte[] image) {
        String newFilename = imageRepository.changeFileNameToUUID(filename);
        imageRepository.saveImage(newFilename, image, UploadFolderType.TMP);
        return newFilename;
    }

    @Override
    public boolean delete(String filename, UploadFolderType uploadFolderType) {

        imageRepository.deleteImage(filename, uploadFolderType);
        if (imageRepository.isImageExists(filename, uploadFolderType)) {
            return true;
        }
        return false;
    }
}
