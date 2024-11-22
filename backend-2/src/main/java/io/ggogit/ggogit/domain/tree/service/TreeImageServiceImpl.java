package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.domain.image.repository.ImageRepositoryImpl;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public String uploadTree(String filename, byte[] image) {
        String newFilename = imageRepository.changeFileNameToUUID(filename);
        imageRepository.saveImage(newFilename, image, UploadFolderType.TREE);
        return newFilename;
    }
    @Override
    public String uploadBook(String filename, byte[] image) {
        String newFilename = imageRepository.changeFileNameToUUID(filename);
        imageRepository.saveImage(newFilename, image, UploadFolderType.BOOK);
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

    @Override
    public String editBookImage(MultipartFile image, String beforeImageName, UploadFolderType uploadFolderType) {
        delete(beforeImageName, uploadFolderType);
        try {
            byte[] imageData = image.getBytes();
            String filename = image.getOriginalFilename();
            String newName = uploadBook(filename, imageData);
            return newName;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
