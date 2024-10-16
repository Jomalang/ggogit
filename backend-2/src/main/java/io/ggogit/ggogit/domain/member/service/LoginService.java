package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final TreeRepository treeRepository;

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

    public Boolean isOnner(HttpServletRequest request, Long treeId){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId = member.getId();
        Tree tree = treeRepository.findById(treeId);
        Long memberIdByTreeId = tree.getMemberId();

        return Objects.equals(memberId, memberIdByTreeId);
    }
}
