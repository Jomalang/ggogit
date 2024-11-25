package io.ggogit.ggogit.domain.member.service;


import io.ggogit.ggogit.api.member.dto.MemberDomainCntResponse;
import io.ggogit.ggogit.api.member.dto.MemberRefreshResponse;
import io.ggogit.ggogit.api.member.dto.MemberResponse;
import io.ggogit.ggogit.domain.member.entity.*;
import io.ggogit.ggogit.domain.member.repository.EmailJoinTokenRepository;
import io.ggogit.ggogit.domain.member.repository.MemberProfileImageRepository;
import io.ggogit.ggogit.domain.member.repository.MemberRepository;
import io.ggogit.ggogit.domain.member.repository.PassWordRestRepository;
import io.ggogit.ggogit.domain.member.repository.query.MemberQueryRepository;
import io.ggogit.ggogit.domain.tree.entity.Tree;
import io.ggogit.ggogit.domain.tree.repository.TreeRepository;
import io.ggogit.ggogit.util.JwtTokenProvider;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final JwtTokenProvider jwtTokenProvider;

    private final EmailJoinTokenRepository emailJoinTokenRepository;
    private final PassWordRestRepository passWordRestRepository;
    private final MemberRepository memberRepository;
    private final MemberProfileImageRepository memberProfileImageRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final TreeRepository treeRepository;

    private final JavaMailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    private final TemplateEngine templateEngine;

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
    public boolean existsEmail(String email, String username) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        return member.getUsername().equals(username);
    }

    @Override
    @Transactional
    public void joinSendEmail(String email) throws MessagingException {

        // 세션에 이메일 토큰 저장
        String token = generateToken();

        StringBuilder emailContent = new StringBuilder();
        emailContent.append("http://localhost:3000/member/new?key=");
        emailContent.append(token);

        // Thymeleaf Context 객체 생성
        Context context = new Context();
        context.setVariable("link", emailContent.toString());
        String htmlContent = templateEngine.process("join-email", context); // HTML 템플릿을 문자열로 변환

        // 이메일 전송 로직
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(email);
        helper.setSubject("[GGogit] 회원가입 안내 이메일 - 회원가입을 환엽합니다~~!!");
        helper.setText(htmlContent, true);
        emailSender.send(message);

        // 이메일 인증 정보 저장
        EmailJoinToken emailJoinToken = EmailJoinToken.of(email, token);
        emailJoinTokenRepository.save(emailJoinToken);
    }

    @Override
    public boolean existsEmailJoinToken(String joinToken) {
        return emailJoinTokenRepository.findByUuid(joinToken).isPresent();
    }

    @Override
    @Transactional
    public void passwordResetSendEmail(String email) throws MessagingException {

        // 기존 이메일 인증 정보 삭제
        passWordRestRepository.deleteByEmail(email);

        String token = generateToken();

        // 이메일 전송 로직
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("http://localhost:3000/member/password-reset?key=");
        emailContent.append(token);

        Context context = new Context();
        context.setVariable("link", emailContent.toString());
        String htmlContent = templateEngine.process("password-reset", context); // HTML 템플릿을 문자열로 변환

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo("gksxorb147@naver.com");
        helper.setSubject("[GGogit] 비밀번호 초기화 안내 이메일");
        helper.setText(htmlContent, true);
        emailSender.send(message);

        // 비밀번호 변경 정보 저장
        PassWordRest passWordRest = PassWordRest.of(email, token);
        passWordRestRepository.save(passWordRest);
    }

    @Override
    @Transactional
    public void passwordReset(String password, String token) {
        PassWordRest passWordRest = passWordRestRepository.findByUuid(token)
                .orElseThrow(() -> new IllegalArgumentException("비밀번호 변경 토큰이 존재하지 않습니다."));

        Member member = memberRepository.findByEmail(passWordRest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        member.setPassword(passwordEncoder.encode(secretKey + password)); // 비밀번호 암호화
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
    public Member join(Member member) {

        // 비밀번호 암호화
        member.setPassword(passwordEncoder.encode(secretKey + member.getPassword()));
        member.setRole(RoleType.USER);

        // 회원 저장
        memberRepository.save(member);

        // 이메일 인증 정보 삭제
        emailJoinTokenRepository.deleteByEmail(member.getEmail());

        return member;
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

        //변경 감지 이용
        //닉네임은 없앨 수 없다.
        if(!toMember.getNickname().isEmpty())
        fromMember.setNickname(toMember.getNickname());

        //소개는 없앨 수 있다.
        fromMember.setIntroduction(toMember.getIntroduction());

        memberRepository.save(fromMember);
    }

    // 이메일 전송 고유 값
    private String generateToken() {
        return UUID.randomUUID().toString();
    }


    @Override
    public MemberResponse findByNickname(String nickname) {
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new EntityNotFoundException(nickname + "은 존재하지 않은 회원입니다."));
        return MemberResponse.of(member); // MemberResponse.of() 메서드에 Member 객체를 전달
    }

    @Override
    public MemberResponse findByUsername(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(username + "은 존재하지 않은 회원입니다."));
        return MemberResponse.of(member);
    }

    @Override
    public MemberResponse findByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(email + "은 존재하지 않은 회원입니다."));
        return MemberResponse.of(member);
    }

    @Override
    public EmailJoinToken findEmailJoinToken(String key) {
        return emailJoinTokenRepository.findByUuid(key)
                .orElseThrow(() -> new IllegalArgumentException("회원 가입 이메일 전송을 진행하지 않은 이메일입니다."));
    }

    @Override
    public PassWordRest findPassWordRest(String key) {
        return passWordRestRepository.findByUuid(key)
                .orElseThrow(() -> new IllegalArgumentException("회원 가입 이메일 전송을 진행하지 않은 이메일입니다."));
    }

    @Override
    public Member getByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(email + "은 존재하지 않은 회원입니다."));
    }

    @Override
    @Transactional
    public Member join(Member member, String profileImage) {

        member.setRole(RoleType.USER);

        // 회원 저장
        memberRepository.save(member);

        // 회원 프로필 이미지 저장
        MemberProfileImage memberProfileImage = MemberProfileImage.of(member, profileImage);
        memberProfileImageRepository.save(memberProfileImage);

        return member;
    }

    @Override
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(memberId + "은 존재하지 않은 회원입니다."));
    }

    @Override
    public MemberDomainCntResponse countDomainById(Long memberId) {
        Long treeCnt = 0L;
        Long leafCnt = 0L;
        Long memoirCnt = 0L;
        Long bookCnt = memberQueryRepository.findBookCntById(memberId);

        List<Tree> trees = treeRepository.findTreeByMemberIdFetch(memberId);
        treeCnt = (long) trees.size();
        leafCnt = trees.stream().mapToLong(tree -> tree.getLeaf().size()).sum();
        memoirCnt = trees.stream().mapToLong(tree -> {
            if(Optional.ofNullable(tree.getMemoir()).isPresent()) return 1L;
            return 0L;
        }).sum();

        return MemberDomainCntResponse.builder()
                .memberId(memberId)
                .treeCnt(treeCnt)
                .leafCnt(leafCnt)
                .memoirCnt(memoirCnt)
                .bookCnt(bookCnt)
                .build();
    }
}