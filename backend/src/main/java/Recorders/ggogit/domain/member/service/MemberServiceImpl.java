package Recorders.ggogit.domain.member.service;

import Recorders.ggogit.domain.member.entity.Member;
import Recorders.ggogit.domain.member.repository.MemberRepository;
import Recorders.ggogit.domain.member.view.MemberImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public void registerMember(Member member) {}

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
        return memberRepository.findById(id);
    }

    @Override
    public MemberImageView getMemberImageView(Long memberId) {
        return memberRepository.getMemberImageView(memberId);
    }
}
