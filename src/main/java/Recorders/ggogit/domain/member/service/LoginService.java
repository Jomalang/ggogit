package Recorders.ggogit.domain.member.service;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Transactional
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(Member member) {
        //TODO 중복되는 이메일일 경우도 처리해줘야 함.
        Optional<Member> findMemberOptional = Optional.ofNullable(memberRepository.findByEmail(member.getEmail()));
        Member foundMember = findMemberOptional.orElse(null);
        if(foundMember != null && member.getPassword().equals(foundMember.getPassword())){
            return foundMember;
        } else{
            return null;
        }
    }

    public Member RegMember(Member member) {
        //TODO 사용자 이름도 받을 건지 결정해야 함.
        memberRepository.save(member);

        return member;
    }

    public Member getMemberByEmail(String email) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByEmail(email));
        return member.orElse(null);
    }

    public Member getMemberByNickname(String nickname) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByNickname(nickname));
        return member.orElse(null);
    }

    // 닉네임 유효성 확인
    public boolean getNickname(String nickname) {
        String ValidNickname = "^[a-zA-Z0-9가-힣_]$";
        return Pattern.matches(ValidNickname, nickname);
    }

    // 이메일 유효성 확인
    public boolean getEmail(String email) {
        String ValidEmail = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(ValidEmail, email);
    }

    //비밀번호 유효성 확인
    public boolean getPassword(String password) {
        String ValidPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_+=\\[\\]{}|;:,.<>?]).{8,32}$";
        return Pattern.matches(ValidPassword, password);
    }

}
