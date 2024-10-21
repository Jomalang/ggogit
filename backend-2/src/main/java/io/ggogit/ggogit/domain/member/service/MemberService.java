package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.domain.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    /**
    * 회원가입
    * @param member 회원 entity
    */
    void registerMember(Member member);

    /**
     * 닉네임 유효성 검사
     * @param nickname 회원 entity
     * @return 유효한 경우 true, 유효하지 않은 경우 false
     */
    boolean getNickname(String nickname);

    /**
     * 이메일 유효성 검사
     * @param email 회원 entity
     * @return 유효한 경우 true, 유효하지 않은 경우 false
     */
    boolean getEmail(String email);

    /**
     * 비밀번호 유효성 검사
     * @param password 회원 entity
     * @return 유효한 경우 true, 유효하지 않은 경우 false
     */
    boolean getPassword(String password);

    Member getMember(Long id);

//    MemberImageView getMemberImageView(Long memberId);
}
