package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.api.member.dto.MemberImageDto;
import io.ggogit.ggogit.api.member.dto.MemberRegRequestDto;
import io.ggogit.ggogit.domain.member.entity.Member;

public interface MemberService {

    /**
     * 회원가입
     *
     * @param memberRegRequestDto 회원가입 요청 DTO
     */
    void registerMember(MemberRegRequestDto memberRegRequestDto);

    /**
     * 닉네임 유효성 검사
     *
     * @param nickname 닉네임
     * @return 유효한 경우 true, 유효하지 않은 경우 false
     */
    boolean getNickname(String nickname);

    /**
     * 이메일 유효성 검사
     *
     * @param email 이메일
     * @return 유효한 경우 true, 유효하지 않은 경우 false
     */
    boolean getEmail(String email);

    /**
     * 비밀번호 유효성 검사
     *
     * @param password 비밀번호
     * @return 유효한 경우 true, 유효하지 않은 경우 false
     */
    boolean getPassword(String password);

    /**
     * ID로 회원 정보 조회
     *
     * @param id 회원 ID
     * @return Member
     */
    Member getMember(Long id);

    /**
     * 회원 ID로 회원 이미지 정보 조회
     *
     * @param memberId 회원 ID
     * @return MemberImageDto
     */
    MemberImageDto getMemberImageDto(Long memberId);
}
