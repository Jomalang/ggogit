package Recorders.ggogit.domain.member.service;

import Recorders.ggogit.domain.member.entity.Member;

public interface MemberService {

    /**
    * 회원가입
    * @param member 회원 entity
    */
    void registerMember(Member member);

}
