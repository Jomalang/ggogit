package io.ggogit.ggogit.domain.memoir.service;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import io.ggogit.ggogit.domain.memoir.repository.MemoirRepository;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MemoirServiceImpl implements MemoirService {

    private final MemoirRepository memoirRepository;
    private final TreeRepository treeRepository;

    @Override
    public Long regMemoir(Memoir memoir, Long treeId) {
        Optional<Tree> opTree = treeRepository.findById(treeId);
        memoir.changeTree(opTree.orElseThrow(()->new IllegalArgumentException("트리가 없습니다.")));
        memoirRepository.save(memoir);
        return memoir.getId();
    }

    @Override
    public void removeMemoir(Long memoirId) {
        memoirRepository.deleteById(memoirId);
    }

    @Override
    public void modifyMemoir(Memoir newMemoir, Long memoirId) {
        Optional<Memoir> opMemoir = memoirRepository.findById(memoirId);
        Memoir memoir = opMemoir.orElseThrow(() -> new IllegalArgumentException("회고록이 없습니다."));
        ChangeMemoir(newMemoir, memoir);
    }

    @Override
    @Transactional(readOnly = true)
    public Memoir getMemoir(Long memoirId) {
        return memoirRepository.findById(memoirId).orElseThrow(()-> new IllegalArgumentException("회고록이 없습니다."));
    }

    @Override
    public boolean isMemoirExist(Long treeId) {
        Optional<Tree> opTree = treeRepository.findById(treeId);
        Tree tree = opTree.orElseThrow(() -> new IllegalArgumentException("트리가 없습니다."));

        Long memoirId = tree.getMemoir().getId();
        return memoirRepository.existsById(memoirId);
    }
    //TODO:나중에 폴더 통합되면 경로 수정해야 함.
    @Override
    public void saveImage(List<String> fileNames) throws IOException {
        final String uploadDir = Paths.get("C:", "ggogit","backend-2", "src", "main", "webapp","image", "tmp").toAbsolutePath().toString();
        //파일 위치 수정
        for(String fileName : fileNames) {
            Path tmpFilePath = Path.of(uploadDir, fileName).toAbsolutePath();
            byte[] tmpFile = Files.readAllBytes(tmpFilePath);
            File newFilePath = new File("C://ggogit/backend-2/src/main/webapp/uploads/image/memoir/" + fileName);
            try{
                Files.write(newFilePath.toPath(), tmpFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //이전 임시 파일 삭제
            Files.delete(tmpFilePath);
        }

    }

    //TODO: getxxx() 시도시 값이 null일경우 검증 로직을 서비스에 추가해야하나?
    private static void ChangeMemoir(Memoir newMemoir, Memoir memoir) {
        memoir.setText(newMemoir.getText());
        memoir.setTitle(newMemoir.getTitle());
        //visibility는 DB에 default값이 있음.
        memoir.setVisibility(newMemoir.getVisibility());
    }
    @Override
    public boolean isOwner(Long memberId, Long memoirId) {

        Memoir memoir = memoirRepository.findById(memoirId)
                .orElseThrow(() -> new IllegalArgumentException("회고록이 없습니다."));

        return Objects.equals(memoir.getTree().getMember().getId(), memberId);
    }

}
