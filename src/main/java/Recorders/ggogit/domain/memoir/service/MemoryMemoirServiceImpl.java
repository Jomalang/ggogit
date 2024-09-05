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
        return 0;
    }

    @Override
    public long modifyMemoir(Memoir memoir, long id) {
        return 0;
    }

    @Override
    public Memoir getMemoir(long treeId) {
        return null;
    }

    @Override
    public List<Memoir> getMemoirs(long memberId) {
        return List.of();
    }

    private static Memoir createMemoir(MemoirForm memoirForm) {
        Memoir memoir = new Memoir();
        memoir.setTreeId(memoirForm.getTreeId());
        memoir.setTitle(memoirForm.getTitle());
        memoir.setText(memoirForm.getText());
        memoir.setVisibility(memoirForm.isVisibility());

        return memoir;
    }
}
