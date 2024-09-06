package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.type.SearchType;
import Recorders.ggogit.domain.leaf.entity.Leaf;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryLeafEtcServiceImpl implements LeafEtcService {

    @Override
    public void register(Leaf leaf) {
    }

    @Override
    public void register(Leaf leaf, Long parentLeafId) {
    }

    @Override
    public boolean isOwner(Leaf leaf) {
        return false;
    }

    @Override
    public void modify(Leaf leaf) {
    }

    @Override
    public void remove(Leaf leaf) {
    }

    @Override
    public void remove(Long leafId) {
    }

    @Override
    public Leaf get(Long leafId) {
        return null;
    }

    @Override
    public List<Leaf> list(Long treeId) {
        return List.of();
    }

    @Override
    public List<Leaf> list(Long treeId, SearchType searchType, String search) {
        return List.of();
    }
}