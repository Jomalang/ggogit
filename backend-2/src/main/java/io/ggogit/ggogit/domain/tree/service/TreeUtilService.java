package io.ggogit.ggogit.domain.tree.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TreeUtilService {

    String updateImageFile(MultipartFile img, String path) throws IOException;


}
