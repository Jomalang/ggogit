package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.api.member.dto.MemberImageDto;
import io.ggogit.ggogit.api.member.dto.MemberRegRequestDto;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberProfileImageRepository;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberProfileImageRepository memberProfileImageRepository;

    private  PasswordEncoder passwordEncoder;

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

    @Override
    public boolean resetPassword(Long userId, String checkPassword, String newPassword) {
        // 사용자 조회
        Optional<Member> memberOptional = memberRepository.findById(userId);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            // 새 비밀번호와 기존 비밀번호가 같을 때 true 반환
            if (newPassword.equals(checkPassword)) {
                return true; // 비밀번호가 같으면 true 반환
            }

            // 기존 비밀번호 확인
            if (passwordEncoder.matches(checkPassword, member.getPassword())) {
                // 비밀번호 변경
                member.setPassword(passwordEncoder.encode(newPassword));
                memberRepository.save(member);
                return true;
            }
        }
        return false; // 사용자 없음 또는 비밀번호 불일치
    }
}
