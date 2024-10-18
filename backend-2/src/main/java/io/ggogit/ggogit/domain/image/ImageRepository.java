package io.ggogit.ggogit.domain.image;

import io.ggogit.ggogit.type.UploadFolderType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Repository
@RequiredArgsConstructor
@AllArgsConstructor
public class ImageRepository {

    @Value("${file.upload-path}")
    private String uploadPath;

    // 이미지 존재 확인
    // 이미지 존재 확인
    public boolean isImageExists(String imageName, UploadFolderType folderType) {
        File file = new File(uploadPath + File.separator + folderType.getFolderName() + File.separator + imageName);
        return file.exists();
    }

    // 이미지 저장
    public void saveImage(String imageName, byte[] imageData, UploadFolderType folderType) {
        File folder = new File(uploadPath + File.separator + folderType.getFolderName());
        if (!folder.exists()) {
            if(!folder.mkdirs()) {
                throw new IllegalArgumentException("이미지 저장 폴더 생성에 실패 하였습니다.");
            }
        }
        File file = new File(folder, imageName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageData);
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 저장에 실패하였습니다.");
        }
    }

    // 이미지 이동
    public void moveImage(String imageName, UploadFolderType sourceFolder, UploadFolderType targetFolder) {
        File sourceFile = new File(uploadPath + File.separator + sourceFolder.getFolderName() + File.separator + imageName);
        File targetFolderFile = new File(uploadPath + File.separator + targetFolder.getFolderName());
        File targetFile = new File(targetFolderFile, imageName);

        if (!targetFolderFile.exists()) {
            if (!targetFolderFile.mkdirs()) {
                throw new IllegalArgumentException("이미지 이동 폴더 생성에 실패하였습니다.");
            }
        }

        if (sourceFile.exists()) {
            if (!sourceFile.renameTo(targetFile)) {
                throw new IllegalArgumentException("이미지 이동에 실패하였습니다.");
            }
        } else {
            throw new IllegalArgumentException("해당 이미지가 존재하지 않습니다.");
        }
    }

    // 이미지 삭제
    public void deleteImage(String imageName, UploadFolderType folderType) {
        File file = new File(uploadPath + File.separator + folderType.getFolderName() + File.separator + imageName);
        if (file.exists()) {
            if (!file.delete()) {
                throw new IllegalArgumentException("이미지 삭제에 실패하였습니다.");
            }
        } else {
            throw new IllegalArgumentException("해당 이미지가 존재하지 않습니다.");
        }
    }

    // 파일이름 UUID로 변경
    public String changeFileNameToUUID(String fileName) {
        return java.util.UUID.randomUUID().toString().replace("-", "") + "." + fileName.split("\\.")[1];
    }
}