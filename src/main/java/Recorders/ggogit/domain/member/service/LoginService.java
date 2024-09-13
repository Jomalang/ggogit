package Recorders.ggogit.domain.member.service;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(Member member) {
        Optional<Member> findMemberOptional = Optional.ofNullable(memberRepository.findByEmail(member.getEmail()));
        Member foundMember = findMemberOptional.orElse(null);
        if(foundMember != null && member.getPassword().equals(foundMember.getPassword())){
            return foundMember;
        } else{
            return null;
        }
    }

    public Member RegMember(Member member) {
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

}
