package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.api.member.dto.MemberImageDto;
import io.ggogit.ggogit.api.member.dto.MemberRegRequestDto;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberProfileImageRepository;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberProfileImageRepository memberProfileImageRepository;

    @Override
    public void registerMember(MemberRegRequestDto memberRegRequestDto) {

    }

    @Override
    public boolean getNickname(String nickname) {

        return false;
    }

    @Override
    public boolean getEmail(String email) {

        return false;
    }

    @Override
    public boolean getPassword(String password) {

        return false;
    }

    @Override
    public Member getMember(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id); // Optional 처리
        if (memberOptional.isPresent()) {
            return memberOptional.get(); // Member 객체 반환
        } else {

            return null;
        }
    }

    @Override
    public MemberImageDto getMemberImageDto(Long memberId) {
        return memberProfileImageRepository.getMemberImageDto(memberId);
    }
}
