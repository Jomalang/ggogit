package io.ggogit.ggogit.domain.image.service;

import io.ggogit.ggogit.domain.image.repository.ImageRepositoryImpl;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepositoryImpl imageRepository;

    public byte[] getImageByte(String imageName, UploadFolderType folderType) {
        if(imageName == null || imageName.isEmpty()) {
            throw new IllegalArgumentException("이미지 이름이 없습니다.");
        }
        if(!imageRepository.isImageExists(imageName, folderType)) {
            throw new IllegalArgumentException("이미지가 존재하지 않습니다.");
        }

        if(folderType == UploadFolderType.TREE) {
            return imageRepository.getImageBytes(imageName, folderType);
        }

        if(folderType == UploadFolderType.BOOK) {
            return imageRepository.getImageBytes(imageName, folderType);
        }

        if(folderType == UploadFolderType.MEMOIR) {
            return imageRepository.getImageBytes(imageName, folderType);
        }

        if(folderType == UploadFolderType.LEAF) {
            return imageRepository.getImageBytes(imageName, folderType);
        }

        if(folderType == UploadFolderType.MEMBER) {
            return imageRepository.getImageBytes(imageName, folderType);
        }
        if(folderType == UploadFolderType.MEMBER_BACKGROUND) {
            return imageRepository.getImageBytes(imageName, folderType);
        }

        else{
            throw new IllegalArgumentException("이미지 폴더 타입이 잘못되었습니다.");
        }
    }
}
