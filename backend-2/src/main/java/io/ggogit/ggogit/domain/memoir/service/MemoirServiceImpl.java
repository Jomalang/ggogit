package io.ggogit.ggogit.domain.memoir.service;

import io.ggogit.ggogit.domain.image.repository.ImageRepositoryImpl;
import io.ggogit.ggogit.domain.memoir.entity.Memoir;
import io.ggogit.ggogit.domain.memoir.repository.MemoirRepository;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
            imageRepository.changeFileNameToUUID(fileName);


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
