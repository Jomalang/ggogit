package Recorders.ggogit.web.memoir.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/tui-editor")
public class MemoirFileApiController {

    //파일을 메모리에 임시 저장할 경로
//    private final String uploadDir = Paths.get("~/workspace", "ggogit", "src", "main", "webapp", "uploads", "image","memoir").toAbsolutePath().toString();
    private final String uploadDir = Paths.get("C:", "ggogit", "src", "main", "webapp","image", "tmp").toAbsolutePath().toString();

    @PostMapping("/image-upload")
    public String uploadEditorImage(@RequestParam final MultipartFile image) {
        if(image.isEmpty()){
            return "";
        }

        String orgFileName = image.getOriginalFilename(); //원본 이미지명
        String uuid = UUID.randomUUID().toString().replaceAll("-", ""); //UUID
        String extension = orgFileName.substring(orgFileName.lastIndexOf(".")); //확장자
        String saveFilename = uuid + "." + extension; //실제 저장될 이미지명
        String fileFullPath = Paths.get(uploadDir, saveFilename).toString(); //실제 이미지가 저장될 경로

        //파일 객체 생성(파일이라고 하지만, 이미지들이 저장될 디렉토리이다.)
        File dir = new File(uploadDir);
        if(!dir.exists()){
            dir.mkdirs(); //경로에 디렉토리가 없다면 생성
        }

        try{
            //파일 저장 과정
            //메모리에 업로드할 이미지 파일 객체 생성
            File uploadedFile = new File(fileFullPath);
            image.transferTo(uploadedFile);
            log.info("saveFilename = {}",saveFilename);
            return saveFilename; //업로드된 파일명 반환

        } catch (IOException e) {
            throw new RuntimeException(e); //TODO 예외처리 해줄 것.
        }
    }

    @GetMapping(value = "/image-print", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] printEditorImage(@RequestParam final String filename) {
        //업로드된 파일의 전체 경로 획득
        String fileFullPath = Paths.get(uploadDir, filename).toString();

        //파일이 없는 경우 예외 처리
        File uploadedFile = new File(fileFullPath);
        if(!uploadedFile.exists()){
            throw new RuntimeException();
        }

        try{
           //이미지 파일을 byte[]로 변환 후 반환
            byte[] imageBytes = Files.readAllBytes(uploadedFile.toPath());
            return imageBytes;
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO 예외 처리 해줄 것.
        }
    }
}
