package io.ggogit.ggogit.domain.member.service;


import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
//    MemberRepository memberRepository;
//
//    @Override
//    public void registerMember(Member member) {}
//
//    @Override
//    public boolean getNickname(String nickname) {
//        return false;
//    }
//
//    @Override
//    public boolean getEmail(String email) {
//        return false;
//    }
//
//    @Override
//    public boolean getPassword(String password) {
//        return false;
//    }
//
//    @Override
//    public Member getMember(Long id) {
//        return memberRepository.findById(id);
//    }
//
//    @Override
//    public MemberImageView getMemberImageView(Long memberId) {
//        return memberRepository.getMemberImageView(memberId);
//    }

    @Override
    public Member register(Member member) {
        // 회원 등록 시 비밀번호 암호화 로직을 추가해야 합니다.
        return memberRepository.save(member);
    }

    @Override
    public Member login(String username, String password) {
        // 사용자 이름으로 회원 조회
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        // 비밀번호 체크 (여기서는 평문 비교를 사용하지만, 실제로는 암호화된 비밀번호를 비교해야 함)
        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return member;
    }

    @Override
    public Optional<String> getEmailFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("email".equals(cookie.getName())) {
                    return Optional.of(cookie.getValue());
                }
            }
        }
        return Optional.empty();
    }
}
