package Recorders.ggogit.domain.member.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void sendEmail(String email) throws MessagingException {

        //발신자, 수신자, 제목 설정
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        mimeMessage.setFrom(new InternetAddress("rhkddlr9899@gmail.com"));
        mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
        mimeMessage.setSubject("꼬깃 회원가입 URL 안내");

        //본문
        mimeMessage.setContent("<html><h1 style='color:#323a27'>꼬깃에 오신걸 환영합니다!<h1></br><h3>아래의 링크로 접속해 회원가입을 진행해 주세요!<h3></br><a target='_blank' href='http://ggogit.io/member/join-input'>꼬깃 회원가입 바로 가기</a></html>", "text/html; charset=utf-8" );
        emailSender.send(mimeMessage);

    }
    public void createEmailCookie(HttpServletResponse response, String email) {
        Cookie cookie = new Cookie("ggogitEmail", email);
        //회원가입 페이지에만 쿠키를 보내도록 설정
        cookie.setPath("/member/join-input");
        cookie.setMaxAge(10*60); //10분동안 쿠키 유지
        response.addCookie(cookie);
    }

    public Optional<String> getEmailCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Optional<String> emailCookie = Optional.empty();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("ggogitEmail")) {
                emailCookie = Optional.ofNullable(cookie.getValue());
            }
        }
        return emailCookie;
    }
}
