package io.ggogit.ggogit.domain.tree.service;

import io.ggogit.ggogit.type.UploadFolderType;

public interface TreeImageService {

    String upload(String filename, byte[] image);

    boolean delete(String filename, UploadFolderType uploadFolderType);
}
