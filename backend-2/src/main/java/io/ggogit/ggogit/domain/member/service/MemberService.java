package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.domain.member.entity.Member;

public interface MemberService {

    Member login(Member member);

    Member registerMember(Member newMember);
}
