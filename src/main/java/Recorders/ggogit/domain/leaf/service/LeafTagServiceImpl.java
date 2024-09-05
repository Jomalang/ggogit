package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.repository.LeafTagRepository;
import Recorders.ggogit.domain.leaf.view.LeafTagView;
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
    public LeafTagView getLeafTagView(Long leafTagId) {
        LeafTag leafTag = leafTagRepository.findById(leafTagId);
        return LeafTagView.of(leafTag);
    }

    @Override
    public List<LeafTagView> getLeafTagViews(Long memberId) {
        return getLeafTagViews(memberId, null);
    }

    @Override
    public List<LeafTagView> getLeafTagViews(Long memberId, String search) {
        return leafTagRepository.findAll(memberId, search).stream()
                .map(LeafTagView::of)
                .toList();
    }
}