package Recorders.ggogit.domain.memoir.service;

import Recorders.ggogit.domain.memoir.entity.Memoir;
import Recorders.ggogit.domain.memoir.repository.MemoirRepository;
import Recorders.ggogit.domain.memoir.vIew.MemoirBookView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemoryMemoirServiceImpl implements MemoirService {

    private final MemoirRepository memoirRepository;

    @Override
    public long regMemoir(Memoir memoir) {
        memoirRepository.save(memoir);
        //생성된 memoir의 id반환
        return memoir.getId();
    }

    @Override
    public long removeMemoir(long treeId) {
        return memoirRepository.delete(treeId);
    }

    @Override
    public void modifyMemoir(Memoir newMemoir, long treeId) {

        memoirRepository.update(newMemoir);
    }

    @Override
    public Memoir getMemoir(long treeId) {
        return memoirRepository.findByTreeId(treeId);
    }

    @Override
    public void imageSave(List<String> fileNames) throws IOException {
        final String uploadDir = Paths.get("C:", "ggogit", "src", "main", "webapp","image", "tmp").toAbsolutePath().toString();
        //파일 위치 수정
        for(String fileName : fileNames) {
            Path tmpFilePath = Path.of(uploadDir, fileName).toAbsolutePath();
            byte[] tmpFile = Files.readAllBytes(tmpFilePath);
            File newFilePath = new File("C://ggogit/src/main/webapp/uploads/image/memoir/" + fileName);
            try{
                Files.write(newFilePath.toPath(), tmpFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //이전 임시 파일 삭제
            Files.delete(tmpFilePath);
        }
    }

    @Override
    public List<MemoirBookView> getMemoirCards(Long memberId) {
        List<MemoirBookView> memoirBookViews = memoirRepository.findMemoirBookViews(memberId);
        return memoirBookViews;
    }


}
