package Recorders.ggogit.domain.memoir.service;

import Recorders.ggogit.domain.memoir.entity.Memoir;
import Recorders.ggogit.web.memoir.MemoirForm;
import Recorders.ggogit.domain.memoir.repository.MemoirRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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
    public long removeMemoir(long id) {
        return memoirRepository.delete(id);
    }

    @Override
    public long modifyMemoir(Memoir newMemoir, long id) {
        Memoir memoir = memoirRepository.findById(id);
        memoir.changeTitle(newMemoir.getTitle());
        memoir.changeText(newMemoir.getText());
        memoir.changeVisibility(newMemoir.getVisibility());

        memoirRepository.update(memoir);
        return memoir.getId();
    }

    @Override
    public Memoir getMemoir(long treeId) {
        return memoirRepository.findByTreeId(treeId);
    }
}
