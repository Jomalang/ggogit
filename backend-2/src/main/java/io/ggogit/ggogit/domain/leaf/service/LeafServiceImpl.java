package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.repository.LeafRepository;
import io.ggogit.ggogit.type.filterType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LeafServiceImpl implements LeafService {
    private final LeafRepository leafRepository;

    @Override
    public HashMap<String, Integer> nodeCountToRoot(Leaf leaf) {
        HashMap<String, Integer> result = new HashMap<>();
        Integer likeCnt = 0;
        Integer viewCnt = 0;
        Integer leafCnt = 1;

        likeCnt = leaf.getLikeCount();
        viewCnt = leaf.getViewCount();


        Leaf tmpLeaf;

        do {
            tmpLeaf = leafRepository.findById(leaf.getParentLeaf().getId()).orElse(null);
            likeCnt += tmpLeaf.getLikeCount();
            viewCnt += tmpLeaf.getViewCount();
            leafCnt++;
        }while (!(tmpLeaf.getParentLeaf() == null));

        result.put("like", likeCnt);
        result.put("view", viewCnt);
        result.put("leaf", leafCnt);

        return result;
    }

    @Override
    public List<Leaf> findBranchByFilter(Long treeId, Boolean owner, Boolean bookMark) {
        return leafRepository.findByBranchQuery(treeId, owner, bookMark);
    }
}
