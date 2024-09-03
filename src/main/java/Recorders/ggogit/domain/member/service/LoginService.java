package Recorders.ggogit.domain.member.service;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.repository.MemberRepository;
import Recorders.ggogit.web.member.LoginForm;
import Recorders.ggogit.web.member.LoginRegForm;
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

}
