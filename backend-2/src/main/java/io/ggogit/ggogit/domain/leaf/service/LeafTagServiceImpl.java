package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.leaf.entity.LeafTagMap;
import io.ggogit.ggogit.domain.leaf.repository.LeafTagMapRepository;
import io.ggogit.ggogit.domain.leaf.repository.LeafTagRepository;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeafTagServiceImpl implements LeafTagService {

    private final LeafTagRepository leafTagRepository;
    private final MemberRepository memberRepository;
    private final LeafTagMapRepository leafTagMapRepository;

    @Override
    @Transactional
    public LeafTag register(Long memberId, String name) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        if (leafTagRepository.findByMemberAndName(member, name).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 태그입니다.");
        }

        LeafTag leafTag = LeafTag.of(member, name);
        return leafTagRepository.save(leafTag);
    }

    @Override
    @Transactional
    public LeafTag modify(Long memberId, Long tagId, String name) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        LeafTag leafTag = leafTagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));

        if (leafTagRepository.findByMemberAndName(member, name).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 태그입니다.");
        }

        leafTag.setName(name);
        return leafTagRepository.save(leafTag);
    }

    @Override
    @Transactional
    public boolean isOwner(Long memberId, Long tagId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        LeafTag leafTag = leafTagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));

        return leafTag.getMember().getId().equals(member.getId());
    }

    @Override
    @Transactional
    public void remove(Long memberId, Long tagId) {

        LeafTag leafTag = leafTagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));

        // 해당 태그를 사용하는 모든 리프 태그 맵을 비활성화 처리
        List<LeafTagMap> leafTags = leafTagMapRepository.findByLeafTag(leafTag);
        for (LeafTagMap leafTagMap : leafTags) {
            leafTagMap.setActive(false);
            leafTagMapRepository.save(leafTagMap);
        }

        leafTag.setIsDeleted(true);
        leafTagRepository.save(leafTag);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LeafTag> list(Long memberId, String searchName, int page, int size) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Pageable pageable = PageRequest.of(page, size);

        return leafTagRepository.findByMemberAndName(member, searchName, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public LeafTag detail(Long tagId) {
        return leafTagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(Long memberId, String name) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        return leafTagRepository.findByMemberAndName(member, name).isPresent();
    }
}