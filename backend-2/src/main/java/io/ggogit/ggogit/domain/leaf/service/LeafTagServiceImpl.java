package io.ggogit.ggogit.domain.leaf.service;

import io.ggogit.ggogit.domain.leaf.entity.LeafTag;
import io.ggogit.ggogit.domain.leaf.repository.LeafTagRepository;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeafTagServiceImpl implements LeafTagService {

    private final LeafTagRepository leafTagRepository;
    private final MemberRepository memberRepository;

    @Override
    public LeafTag register(Long memberId, String name) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        leafTagRepository.findByMemberAndNameContaining(member, name)
                .orElseThrow(() -> new IllegalArgumentException("이미 존재하는 태그입니다."));

        LeafTag leafTag = LeafTag.of(member, name);
        return leafTagRepository.save(leafTag);
    }

    @Override
    public LeafTag modify(Long memberId, Long tagId, String name) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        LeafTag leafTag = leafTagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));

        leafTagRepository.findByMemberAndNameContaining(member, name)
                .orElseThrow(() -> new IllegalArgumentException("이미 존재하는 태그입니다."));

        leafTag.setName(name);
        return leafTagRepository.save(leafTag);
    }

    @Override
    public boolean isOwner(Long memberId, Long tagId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        LeafTag leafTag = leafTagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));

        return leafTag.getMember().getId().equals(member.getId());
    }

    @Override
    public void remove(Long memberId, Long tagId) {
            LeafTag leafTag = leafTagRepository.findById(tagId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));
            leafTagRepository.delete(leafTag);
    }

    @Override
    public Page<LeafTag> list(Long memberId, String search, int page, int size) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Pageable pageable = PageRequest.of(page, size);

        return leafTagRepository.findByList(member, search, pageable);
    }
}