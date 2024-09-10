package Recorders.ggogit.domain.leaf.service;

import Recorders.ggogit.domain.leaf.entity.LeafTag;
import Recorders.ggogit.domain.leaf.repository.LeafTagRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LeafTagServiceImpl implements LeafTagService {

    @Autowired
    private LeafTagRepository leafTagRepository;

    @Override
    public LeafTag register(LeafTag leafTag) {

        // 존재 여부 확인
        Long exist = leafTagRepository
                .existsByMemberIdAndName(leafTag.getMemberId(), leafTag.getName());
        if (exist == 1) {
            throw new IllegalArgumentException("이미 존재하는 태그입니다.");
        }

        // 저장
        Long id =  leafTagRepository.save(leafTag);
        return Optional.ofNullable(leafTagRepository.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("태그 등록에 실패하였습니다."));
    }

    @Override
    public boolean isOwner(Long memberId, Long leafTagId) {
        LeafTag leafTag = Optional.ofNullable(leafTagRepository.findById(leafTagId))
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));
        return leafTag.getMemberId().equals(memberId);
    }

    @Override
    public LeafTag modify(LeafTag leafTag) {
        LeafTag origin = Optional.ofNullable(leafTagRepository.findById(leafTag.getId()))
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));
        origin.setName(leafTag.getName());
        leafTagRepository.update(origin);
        return origin;
    }

    @Override
    public boolean remove(LeafTag leafTag) {
        return remove(leafTag.getMemberId(), leafTag.getId());
    }

    @Override
    public boolean remove(Long memberId, Long leafTagId) {
        assert memberId != null;
        assert leafTagId != null;

        if (!isOwner(memberId, leafTagId)) {
            throw new IllegalArgumentException("해당 태그에 대한 권한이 없습니다.");
        }

        leafTagRepository.deleteById(leafTagId);
        return true;
    }

    @Override
    public LeafTag getLeafTag(Long leafTagId) {
        return Optional.ofNullable(leafTagRepository.findById(leafTagId))
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));
    }

    @Override
    public List<LeafTag> getLeafTags(Long memberId) {
        return getLeafTags(memberId, null, 1L, 10L);
    }

    @Override
    public List<LeafTag> getLeafTags(Long memberId, Long page, Long size) {
        return getLeafTags(memberId, null, page, size);
    }

    @Override
    public List<LeafTag> getLeafTags(Long memberId, @Nullable String search, Long page, Long size) {
        Long total = leafTagRepository.count(memberId, search);

        if (total == 0) { // 검색 결과가 없는 경우
            return List.of();
        }

        long offset = (page - 1) * size;
        if (total < offset) { // total 보다 큰 페이지 요청시 마지막 페이지로 변경
            offset = (total / size) * size;
        }

        return leafTagRepository.findAll(memberId, search, offset, size);
    }
}