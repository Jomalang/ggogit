package Recorders.ggogit.domain.member.service;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.repository.MemberRepository;
import Recorders.ggogit.web.member.form.LoginForm;
import Recorders.ggogit.web.member.form.LoginRegForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(LoginForm loginForm) {
        //TODO 중복되는 이메일일 경우도 처리해줘야 함.
        Optional<Member> findMemberOptional = Optional.ofNullable(memberRepository.findByEmail(loginForm.getEmail()));
        Member member = findMemberOptional.orElse(null);
        if(member != null && member.getPassword().equals(loginForm.getPassword())){
            return member;
        } else{
            return null;
        }
    }

    public Member RegMember(LoginRegForm loginRegForm) {
        Member member = new Member();
        //TODO 사용자 이름도 받을 건지 결정해야 함.
        member.setEmail(loginRegForm.getEmail());
        member.setPassword(loginRegForm.getPassword());
        member.setNickname(loginRegForm.getNickname());
        member.setIntroduction(loginRegForm.getIntroduction());

        memberRepository.save(member);

        return member;
    }

    public Member getMemberByEmail(String email) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByEmail(email));
        return member.orElse(null);
    }

    public Member getMemberBynickname(String nickname) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByNickname(nickname));
        return member.orElse(null);
    }
}
