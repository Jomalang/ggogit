package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.Type.SearchType;
import Recorders.ggogit.domain.leaf.view.LeafBookView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryLeafBookServiceImpl implements LeafBookService{

    @Override
    public void register(LeafBookView leafBookView) {

    }

    @Override
    public void register(LeafBookView leafBookView, Long parentLeafId) {

    }

    @Override
    public boolean isOwner(LeafBookView leafBookView) {
        return false;
    }

    @Override
    public void modify(LeafBookView leafBookView) {

    }

    @Override
    public void remove(LeafBookView leafBookView) {

    }

    @Override
    public LeafBookView get(Long leafBookId) {
        return null;
    }

    @Override
    public List<LeafBookView> list(Long treeId) {
        return List.of();
    }

    @Override
    public List<LeafBookView> list(Long treeId, SearchType searchType, String search) {
        return List.of();
    }
}
