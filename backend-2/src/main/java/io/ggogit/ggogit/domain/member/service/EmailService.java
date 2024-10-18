package io.ggogit.ggogit.domain.member.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.Cookie;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendEmail(String to) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject("회원 가입 확인 이메일");
        helper.setText("회원 가입을 환영합니다. 이 이메일은 회원 가입 확인용입니다.", false);

        mailSender.send(message);
    }

    public void createEmailCookie(Model model, String email) {
        model.addAttribute("email", email);
    }

    public void removeEmailCookie(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
    }

    public Optional<String> getEmailCookie(HttpServletRequest request) {
        return null;
    }
}
