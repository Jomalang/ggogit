package io.ggogit.ggogit.domain.tree.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class TreeUrilServiceImpl implements TreeUtilService {

    @Override
    public String updateImageFile(MultipartFile img, String path) throws IOException {


        String fileName = img.getOriginalFilename();
        String fullPath = path + File.separator + fileName;

        File filePath = new File(path);
        if(!filePath.exists())
            filePath.mkdirs();

        img.transferTo(new File(fullPath));

        return fullPath;
    }
}
