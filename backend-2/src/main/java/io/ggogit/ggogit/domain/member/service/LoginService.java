package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.api.member.dto.MemberLoginRequestDto;
import io.ggogit.ggogit.api.member.dto.MemberRegRequestDto;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final TreeRepository treeRepository;

    public Member login(MemberLoginRequestDto loginRequestDto) {
        Optional<Member> findMemberOptional = Optional.ofNullable(memberRepository.findByEmail(loginRequestDto.getEmail()));
        Member foundMember = findMemberOptional.orElse(null);
        if (foundMember != null && loginRequestDto.getPassword().equals(foundMember.getPassword())) {
            return foundMember;
        } else {
            return null;
        }
    }

    public Member regMember(MemberRegRequestDto regRequestDto) {
        Member member = regRequestDto.toMember();
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

    public Boolean isOnner(HttpServletRequest request, Long treeId) {

        Member member = (Member) request.getSession().getAttribute("SID");
        if (member == null) {
            return false;
        }
        Long memberId = member.getId();
        Optional<Tree> treeOptional = treeRepository.findById(treeId); // Optional 처리
        if (treeOptional.isPresent()) { // Tree 존재 여부 확인
            Tree tree = treeOptional.get();
            Long memberIdByTreeId = tree.getMember().getId();

            return Objects.equals(memberId, memberIdByTreeId);
        } else {

            return false;
        }
    }

}
