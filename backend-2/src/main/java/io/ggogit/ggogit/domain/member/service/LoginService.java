package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import io.ggogit.ggogit.api.member.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Member> login(Member member) {
        Optional<Member> findMemberOptional = Optional.ofNullable(memberRepository.findByEmail(member.getEmail()));
        Member foundMember = findMemberOptional.orElse(null);

        if (foundMember != null && member.getPassword().equals(foundMember.getPassword())) {
            return ResponseEntity.ok(foundMember); // Return HTTP 200 with member details
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Return HTTP 401 for unauthorized access
        }
    }

    public ResponseEntity<Member> registerMember(Member member) {
        memberRepository.save(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(member); // Return HTTP 201 upon successful registration
    }

    public ResponseEntity<Member> getMemberByEmail(String email) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByEmail(email));
        return member
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); // Return HTTP 404 if not found
    }

    public ResponseEntity<Member> getMemberByNickname(String nickname) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByNickname(nickname));
        return member
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); // Return HTTP 404 if not found
    }

    public ResponseEntity<Boolean> isOwner(HttpServletRequest request, Long treeId) {
        return null;
    }

    public void RegMember(Member newMember) {
    }
}
