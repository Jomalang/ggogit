package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.api.member.dto.MemberRefreshResponse;
import io.ggogit.ggogit.domain.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public interface MemberService {

    boolean existsEmail(@Email String email);

    void joinSendEmail(@Email String email);

    boolean existsEmailJoinToken(@Email String email, @NotBlank String joinToken);

    void join(Member member);

    boolean validateToken(String refreshToken);

    void edit(Long id, Member member);

    MemberRefreshResponse refresh(String refreshToken);

    String generateAccessToken(Member member);

    boolean loginCheck(Member member);

    String generateRefreshToken(Member member);

    void passwordResetSendEmail(String email);

    void passwordReset(String password, String token);

    void deleteJoinTmpEmailInfo(String email);
}