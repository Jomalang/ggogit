package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.api.leaf.dto.LeafBeforeInfoResponse;
import io.ggogit.ggogit.api.leaf.dto.LeafDetailResponse;
import io.ggogit.ggogit.domain.leaf.entity.Leaf;
import io.ggogit.ggogit.domain.leaf.entity.LeafBook;
import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.leaf.entity.LeafTagMap;
import io.ggogit.ggogit.domain.leaf.repository.LeafBookRepository;
import io.ggogit.ggogit.domain.leaf.repository.LeafRepository;
import io.ggogit.ggogit.domain.leaf.repository.LeafTagMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeafDtoServiceImpl implements LeafDtoService {

    private final LeafRepository leafRepository;
    private final LeafTagMapRepository leafTagMapRepository;
    private final LeafBookRepository leafBookRepository;

    @Override
    public LeafBeforeInfoResponse findLeafBeforeInfo(Long leafId) {

        Leaf leaf = leafRepository.findById(leafId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(leaf);
        List<LeafTag> leafTags = leafTagMaps.stream().map(LeafTagMap::getLeafTag).toList();

        return LeafBeforeInfoResponse.of(leaf, leafTags);
    }

    @Override
    public LeafDetailResponse findLeafBookDetail(Long leafId) {

        Leaf leaf = leafRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프가 존재하지 않습니다."));

        LeafBook leafBook = leafBookRepository.findById(leafId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리프의 책이 존재하지 않습니다."));

        List<LeafTagMap> leafTagMaps = leafTagMapRepository.findByLeaf(leaf);
        List<LeafTag> leafTags = leafTagMaps.stream().map(LeafTagMap::getLeafTag).toList();

        return LeafDetailResponse.of(leaf, leafBook, leafTags);
    }
}
