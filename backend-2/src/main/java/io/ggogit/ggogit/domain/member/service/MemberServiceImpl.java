package io.ggogit.ggogit.domain.member.service;

import io.ggogit.ggogit.api.member.dto.MemberRefreshResponse;
import io.ggogit.ggogit.domain.member.entity.EmailJoinToken;
import io.ggogit.ggogit.domain.member.entity.Member;
import io.ggogit.ggogit.domain.member.entity.PassWordRest;
import io.ggogit.ggogit.domain.member.entity.RoleType;
import io.ggogit.ggogit.domain.member.repository.EmailJoinTokenRepository;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import io.ggogit.ggogit.domain.member.repository.PassWordRestRepository;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final EmailJoinTokenRepository emailJoinTokenRepository;
    private final PassWordRestRepository passWordRestRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final JavaMailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-expiration}")
    private long accessExpirationTime;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpirationTime;

    @Override
    public boolean existsEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public void joinSendEmail(String email) {

        // 세션에 이메일 토큰 저장
        String token = generateToken();

        StringBuilder emailContent = new StringBuilder();
        emailContent.append("http://localhost:8080/member/join?key=");
        emailContent.append(token);

        // 이메일 전송 로직
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("gksxorb147@naver.com");
        message.setSubject("회원가입 인증 이메일"); // TODO: 나중에 메일 제목 변경
        message.setText(emailContent.toString()); // TODO: 나중에 메일 내용 변경
        message.setFrom("your_email@gmail.com"); // 발신자 이메일
        emailSender.send(message);

        // 이메일 인증 정보 저장
        EmailJoinToken emailJoinToken = EmailJoinToken.of(email, token);
        emailJoinTokenRepository.save(emailJoinToken);
    }

    @Override
    public void passwordResetSendEmail(String email) {

        // 기존 이메일 인증 정보 삭제
        passWordRestRepository.deleteByEmail(email);

        String token = generateToken();

        // 이메일 인증 정보 저장
        PassWordRest passWordRest = PassWordRest.of(email, token);
        passWordRestRepository.save(passWordRest);

        // 이메일 전송 로직
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("http://localhost:8080/member/password-reset?key=");
        emailContent.append(token);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("gksxorb147@naver.com");
        message.setSubject("비밀번호 변경 이메일"); // TODO: 나중에 메일 제목 변경
        message.setText(emailContent.toString()); // TODO: 나중에 메일 내용 변경
        message.setFrom("your_email@gmail.com"); // 발신자 이메일
        emailSender.send(message);
    }

    @Override
    @Transactional
    public void passwordReset(String password, String token) {
        PassWordRest passWordRest = passWordRestRepository.findByUuid(token)
                .orElseThrow(() -> new IllegalArgumentException("비밀번호 변경 토큰이 존재하지 않습니다."));

        Member member = memberRepository.findByEmail(passWordRest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        member.setPassword(password);
        memberRepository.save(member);

        passWordRestRepository.deleteByEmail(passWordRest.getEmail());
    }

    @Override
    @Transactional
    public void deleteJoinTmpEmailInfo(String email) {
        emailJoinTokenRepository.deleteByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsEmailJoinToken(String email, String joinToken) {
        EmailJoinToken emailJoinToken = emailJoinTokenRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원 가입 이메일 전송을 진행하지 않은 이메일입니다."));
        return emailJoinToken.getUuid().equals(joinToken);
    }

    @Override
    @Transactional
    public void join(Member member) {

        // 비밀번호 암호화
        member.setPassword(passwordEncoder.encode(secretKey + member.getPassword()));
        member.setRole(RoleType.USER);

        // 회원 저장
        memberRepository.save(member);

        // 이메일 인증 정보 삭제
        emailJoinTokenRepository.deleteByEmail(member.getEmail());
    }

    @Override
    public boolean validateToken(String refreshToken) {
        return jwtTokenProvider.validateToken(refreshToken);
    }

    @Override
    public MemberRefreshResponse refresh(String refreshToken) {

        // 회원 조회
        String email = jwtTokenProvider.extractSubject(refreshToken);

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        // JWT 토큰 재발급
        String token = jwtTokenProvider.generateAccessToken(member);

        return MemberRefreshResponse.of(token, accessExpirationTime);
    }

    @Override
    public boolean loginCheck(Member member) {
        // 회원 조회
        Member findMember = memberRepository.findByEmail(member.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        return passwordEncoder.matches(secretKey + member.getPassword(), findMember.getPassword());
    }

    @Override
    public String generateAccessToken(Member member) {
        return jwtTokenProvider.generateAccessToken(member);
    }

    @Override
    public String generateRefreshToken(Member member) {
        return jwtTokenProvider.generateRefreshToken(member);
    }

    @Override
    @Transactional
    public void edit(Long id, Member toMember) {
        Member fromMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        fromMember.setUsername(toMember.getUsername());
        fromMember.setNickname(toMember.getNickname());
        fromMember.setIntroduction(toMember.getIntroduction());

        memberRepository.save(fromMember);
    }

    // 이메일 전송 고유 값
    private String generateToken() {
        return UUID.randomUUID().toString();
    }


    @Override
    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public Optional<Member> findByTrees(List<Tree> trees) {
        return memberRepository.findByTrees(trees);
    }
}