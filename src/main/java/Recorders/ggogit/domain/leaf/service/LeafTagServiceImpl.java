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
    public void register(LeafTag leafTag) {

    }

    @Override
    public boolean isOwner(LeafTag leafTag) {
        return false;
    }

    @Override
    public void modify(LeafTag leafTag) {

    }

    @Override
    public void remove(LeafTag leafTag) {

    }

    @Override
    public void remove(Long leafTagId) {

    }

    @Override
    public LeafTagView getLeafTagView(Long leafTagId) {
        return null;
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