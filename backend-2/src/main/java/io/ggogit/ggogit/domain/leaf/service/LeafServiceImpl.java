package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.api.leaf.dto.LeafBranchResponse;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.repository.LeafRepository;
import io.ggogit.ggogit.type.filterType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<LeafBranchResponse> findBranchByFilter(Long treeId, Boolean owner, Boolean bookMark) {
        List<Leaf> leafList = leafRepository.findByBranchQuery(treeId, owner, bookMark);

        List<LeafBranchResponse> responseList = new ArrayList<>();
        for (Leaf leaf : leafList) {
            HashMap<String, Integer> result = nodeCountToRoot(leaf);

            Integer likeCnt = result.get("like");
            Integer viewCnt = result.get("view");
            Integer leafCnt = result.get("leaf");

            responseList.add(LeafBranchResponse.of(leaf, likeCnt, viewCnt, leafCnt));
        }


        return responseList;
    }
}
