package Recorders.ggogit.domain.tree.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface TreeUtilService {

    String updateImageFile(MultipartFile img, String path) throws IOException;

}
