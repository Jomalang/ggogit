package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.view.LeafTagView;

import java.util.List;


public class MemoryLeafTagServiceImpl implements LeafTagService {

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
        return List.of(
                LeafTagView.builder().id(1L).memberId(memberId).name("태그1").build(),
                LeafTagView.builder().id(2L).memberId(memberId).name("태그2").build(),
                LeafTagView.builder().id(3L).memberId(memberId).name("태그3").build(),
                LeafTagView.builder().id(4L).memberId(memberId).name("태그4").build(),
                LeafTagView.builder().id(5L).memberId(memberId).name("태그5").build(),
                LeafTagView.builder().id(6L).memberId(memberId).name("태그6").build(),
                LeafTagView.builder().id(7L).memberId(memberId).name("태그7").build(),
                LeafTagView.builder().id(8L).memberId(memberId).name("태그8").build(),
                LeafTagView.builder().id(9L).memberId(memberId).name("태그9").build(),
                LeafTagView.builder().id(10L).memberId(memberId).name("태그10").build(),
                LeafTagView.builder().id(11L).memberId(memberId).name("태그11").build(),
                LeafTagView.builder().id(12L).memberId(memberId).name("태그12").build(),
                LeafTagView.builder().id(13L).memberId(memberId).name("태그13").build(),
                LeafTagView.builder().id(14L).memberId(memberId).name("태그14").build(),
                LeafTagView.builder().id(15L).memberId(memberId).name("태그15").build(),
                LeafTagView.builder().id(1L).memberId(memberId).name("태그16").build()
        );
    }

    @Override
    public List<LeafTagView> getLeafTagViews(Long memberId, String search) {
        return List.of();
    }
}
