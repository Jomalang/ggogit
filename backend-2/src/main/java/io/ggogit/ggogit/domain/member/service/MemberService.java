package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.api.member.dto.MemberRefreshResponse;
import io.ggogit.ggogit.api.member.dto.MemberResponse;
import io.ggogit.ggogit.domain.member.entity.EmailJoinToken;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.entity.PassWordRest;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public interface MemberService {

    boolean existsEmail(@Email String email);

    boolean existsEmail(@Email String email, String username);

    void joinSendEmail(@Email String email) throws MessagingException;

    boolean existsEmailJoinToken(String joinToken);

    boolean existsEmailJoinToken(String email, String joinToken);

    Member join(Member member);

    boolean validateToken(String refreshToken);

    void edit(Long id, Member member);

    MemberRefreshResponse refresh(String refreshToken);

    String generateAccessToken(Member member);

    boolean loginCheck(Member member);

    String generateRefreshToken(Member member);

    void passwordResetSendEmail(String email) throws MessagingException;

    void passwordReset(String password, String token);

    void deleteJoinTmpEmailInfo(String email);

    MemberResponse findByNickname(String nickname);

    MemberResponse findByUsername(String username);

    MemberResponse findByEmail(String email);

    EmailJoinToken findEmailJoinToken(String key);

    PassWordRest findPassWordRest(String key);

    Member getByEmail(String email);

    Member join(Member member, String profileImage);
}