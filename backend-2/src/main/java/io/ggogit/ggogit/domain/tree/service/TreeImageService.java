package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.type.UploadFolderType;
import org.springframework.web.multipart.MultipartFile;

public interface TreeImageService {

    String upload(String filename, byte[] image);

    String uploadTree(String filename, byte[] image);

    String uploadBook(String filename, byte[] image);

    boolean delete(String filename, UploadFolderType uploadFolderType);

    String editBookImage(MultipartFile image, String beforeImageName, UploadFolderType uploadFolderType);
}
