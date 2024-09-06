package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.repository.LeafTagRepository;
import Recorders.ggogit.domain.leaf.view.LeafTagView;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LeafTagServiceImpl implements LeafTagService {

    @Autowired
    private LeafTagRepository leafTagRepository;

    @Override
    public LeafTag register(LeafTag leafTag) {
        Long id =  leafTagRepository.save(leafTag);
        leafTag.setId(id);
        return leafTag;
    }

    @Override
    public boolean isOwner(Long memberId, Long leafTagId) {
        LeafTag leafTag = leafTagRepository.findById(leafTagId);
        if (leafTag == null) return false;
        return leafTag.getMemberId().equals(memberId);
    }

    @Override
    public boolean modify(LeafTag leafTag) {
        LeafTag origin = leafTagRepository.findById(leafTag.getId());
        if (origin == null) return false;
        origin.setName(leafTag.getName()); // TODO 예외처리 해야함
        Long id = leafTagRepository.save(origin);
        return id != null;
    }

    @Override
    public boolean remove(LeafTag leafTag) {
        leafTagRepository.delete(leafTag);
        return true;
    }

    @Override
    public boolean remove(Long leafTagId) {
        leafTagRepository.deleteById(leafTagId);
        return true;
    }
    @Override
    public LeafTag getLeafTag(Long leafTagId) {
        return leafTagRepository.findById(leafTagId);
    }

    @Override
    public List<LeafTagView> getLeafTags(Long memberId) {
        return getLeafTags(memberId, null, 1L, 10L);
    }

    @Override
    public List<LeafTagView> getLeafTags(Long memberId, Long page, Long size) {
        return getLeafTags(memberId, null, page, size);
    }

    @Override
        public List<LeafTagView> getLeafTags(Long memberId, @Nullable String search, Long page, Long size) {
        Long total = leafTagRepository.count(memberId, search);

        // 데이터가 없는 경우
        if (total == 0) { return List.of(); }

        long offset = (page - 1) * size;
        if (total < offset) { // total 보다 큰 페이지 요청시 마지막 페이지로 변경
            offset = (total / size) * size;
        }

        return leafTagRepository.findAll(memberId, search, offset, size)
                .stream().map(LeafTagView::of).toList();
    }
}