package io.ggogit.ggogit.domain.member.service;


import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(Member member) {
        return memberRepository.findByEmailAndPassword(member.getEmail(), member.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    }
}
