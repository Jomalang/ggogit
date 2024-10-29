package io.ggogit.ggogit.domain.image.repository;

import io.ggogit.ggogit.type.UploadFolderType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@RequiredArgsConstructor
@AllArgsConstructor
public class ImageRepositoryImpl {

    @Value("${file.upload-path}")
    private String uploadPath;


    // 이미지 존재 확인
    /**
     *
     * @param imageName 존재 여부를 확인할 이미지의 파일명입니다.
     * @param folderType 이미지가 존재하는 폴더의 타입입니다. (UploadFolderType)
     * @return
     */
    public boolean isImageExists(String imageName, UploadFolderType folderType) {
        File file = new File(uploadPath + File.separator + folderType.getFolderName() + File.separator + imageName);
        return file.exists();
    }

    // 이미지 저장
    /**
     *
     * @param imageName 저장할 이미지의 UUID 파일명입니다.(확장자 포함되어야 함.)
     * @param imageData 저장할 이미지의 byte배열입니다.
     * @param folderType 이미지가 저장될 폴더의 타입입니다. (UploadFolderType)
     */
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
    /**
     *
     * @param imageName 이동할 이미지의 파일명입니다.
     * @param sourceFolder 이미지가 이전에 존재하던 폴더의 타입입니다. (UploadFolderType)
     * @param targetFolder 이미지가 이동할 폴더의 타입입니다.(UploadFolderType)
     */
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
    /**
     *
     * @param imageName 삭제할 이미지의 파일명입니다.
     * @param folderType 이미지가 존재하는 폴더의 타입입니다. (UploadFolderType)
     */
    public void deleteImage(String imageName, UploadFolderType folderType) {
        File file = new File(uploadPath + File.separator + folderType.getFolderName() + File.separator + imageName);
        if (!file.exists()) {
            return; // 이미지가 존재하지 않으면 PASS
        }
        if (!file.delete()) {
            throw new IllegalArgumentException("이미지 삭제에 실패하였습니다.");
        }
    }

    // 이미지 byte[]로 반환

    /**
     *
     * @param imageName 이미지의 파일명입니다.
     * @param folderType 이미지가 존재하는 폴더의 타입입니다. (UploadFolderType)
     * @return 이미지의 byte 배열
     */
    public byte[] getImageBytes(String imageName, UploadFolderType folderType) {
        File file = new File(uploadPath + File.separator + folderType.getFolderName() + File.separator + imageName);
        if (!file.exists()) {
            throw new IllegalArgumentException("이미지가 존재하지 않습니다.");
        }
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 읽기에 실패하였습니다.");
        }
    }

    // 파일이름 UUID로 변경
    /**
     *
     * @param fileName 저장할 이미지의 Original 파일명을 받습니다.
     * @return UUID로 변경된 파일명
     */
    public String changeFileNameToUUID(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        return java.util.UUID.randomUUID().toString().replace("-", "") + "." + extension;
    }

    /**
     *
     * @param fileName 파일 이름을 입력받습니다.
     * @param folderType 찾기를 원하는 폴더 타입을 적습니다.
     * @return 이미지의 전체 경로를 반환합니다.
     */
    public Path getImageFullPath(String fileName, UploadFolderType folderType) {
        Path path = Paths.get(uploadPath + File.separator + folderType.getFolderName() + File.separator + fileName);
        return path.toAbsolutePath();
    }

    /**
     * 마크다운 문법안의 이미지들을 모두 옮겨 줍니다.
     * @param content 이미지 파일명을 추출할 문자열을 입력받습니다. 토스트 에디터의 마틐다운 형식을 가정합니다.
     * @param sourceFolder 이미지 파일이 존재하는 폴더 타입을 입력받습니다.
     * @param targetFolder 이미지 파일을 이동할 폴더 타입을 입력받습니다.
     */
    public void moveAllImages(String content, UploadFolderType sourceFolder, UploadFolderType targetFolder) {
        String regex = "filename=([\\w.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String fileName = matcher.group(1);
            moveImage(fileName, sourceFolder, targetFolder);
        }
    }
}