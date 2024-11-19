package io.ggogit.ggogit.domain.memoir.service;

import io.ggogit.ggogit.domain.image.repository.ImageRepositoryImpl;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import io.ggogit.ggogit.domain.memoir.repository.MemoirRepository;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import io.ggogit.ggogit.type.UploadFolderType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MemoirServiceImpl implements MemoirService {

    private final MemoirRepository memoirRepository;
    private final TreeRepository treeRepository;
    private final ImageRepositoryImpl imageRepository;

    @Override
    public Long regMemoir(Memoir memoir, Long treeId) {
        Optional<Tree> opTree = treeRepository.findById(treeId);
        memoir.changeTree(opTree.orElseThrow(()->new IllegalArgumentException("트리가 없습니다.")));
        memoirRepository.save(memoir);
        return memoir.getId();
    }

    @Override
    public void removeMemoir(Long memoirId) {
        Optional<Memoir> opMemoir = memoirRepository.findById(memoirId);
        Memoir memoir = opMemoir.orElseThrow(() -> new IllegalArgumentException("회고록이 없습니다."));

        //TODO: 이미지 테이블 따로 뺄것.
        //이미지 삭제
        String memoirText = memoir.getText();
        List<String> imageNames = new ArrayList<>();
        //memoirText에서 <img>태그의 src속성값을 추출
        Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher matcher = pattern.matcher(memoirText);
        while (matcher.find()) {
            log.info("fileName = {}", matcher.group(1));
            imageNames.add(matcher.group(1));
        }

        while (!imageNames.isEmpty()) {
            String imageName = imageNames.removeFirst();
            imageRepository.deleteImage(imageName, UploadFolderType.MEMOIR);
        }

        //트리 연결 해제
        memoir.getTree().setMemoir(null);
        memoir.changeTree(null);
        //is_deleted true로 변경
        memoir.setIsDeleted(true);
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

       Optional<Memoir> opMemoir = Optional.ofNullable(tree.getMemoir());
       log.info("opMemoir.get() = {}", opMemoir);
       return opMemoir.isPresent();
    }
    //TODO:나중에 폴더 통합되면 경로 수정해야 함.
    @Override
    public void saveImage(List<String> fileNames) throws IOException {
        if(fileNames.isEmpty()) return;
        for (String fileName : fileNames) {
            if(imageRepository.isImageExists(fileName, UploadFolderType.TMP)){
                imageRepository.moveImage(fileName, UploadFolderType.TMP, UploadFolderType.MEMOIR);
            }
            imageRepository.deleteImage(fileName, UploadFolderType.TMP);
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
