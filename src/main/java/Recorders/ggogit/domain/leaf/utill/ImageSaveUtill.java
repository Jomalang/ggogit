package Recorders.ggogit.domain.leaf.utill;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ImageSaveUtill {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.tmp-dir}")
    private String tmpDir;

    public String extractFileName(String fullPath) {
        Path path = Paths.get(fullPath);
        return path.getFileName().toString();
    }

    public String moveImageFile(String fileName, String directory, boolean changeFileNameToUUID) {
        Path from = Path.of(tmpDir, fileName);
        
        if (changeFileNameToUUID) { // changeFileNameToUUID가 true일 경우 파일 이름 변경
            fileName = UUID.randomUUID().toString() // 파일 이름 변경
                    .replace("-", "") + "." + fileName.split("\\.")[1];
        }

        Path to = Paths.get(uploadDir, "image", directory, fileName);
        try {
            if (!Files.exists(to.getParent())) { // 폴더 경로 존재 확인
                Files.createDirectories(to.getParent());
            }
            Files.move(from, to);
            return to.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("이미지 파일 이동 실패", e);
        }
    }

    public String moveImageFile(String fileName, String directory) {
        return moveImageFile(fileName, directory, false);
    }

    public String changeTagImageSrc(String content, String fileName) {
        String before = "/api/v1/leaf/image-print?filename=" + fileName;
        String after = "/uploads/image/leaf/" + fileName;
        return content.replace(before, after);
    }

    public List<String> extractImageFileNames(String content) {
        List<String> fileNames = new ArrayList<>();
        // 문자열에서 이미지 태그 추출
        String regex = "src\\s*=\\s*\"?(.+?)(\"|\\s|>)"; // 이미지 태그 의 src 속성 추출
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        // 이미지 태그에서 파일명 추출
        Matcher matcher = pattern.matcher(content);

        // 리스트에 저장
        while (matcher.find()) {
            String srcAttribute = matcher.group();
            String imagePath = srcAttribute.substring(5, srcAttribute.length() - 1); // src="{}"제거 < 내용만 추출하기 위함
            String fileName = imagePath.split("filename=")[1]; // 파일 이름 추출
            fileNames.add(fileName);
        }

        return fileNames;
    }
}