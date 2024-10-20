package io.ggogit.ggogit.domain.member.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String toEmail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(sender);
        helper.setTo(toEmail);
        helper.setSubject("Ggogit 회원가입 인증");
        helper.setText("<h1>Ggogit</h1>" +
                "<br/>" +
                "<p>안녕하세요. Ggogit입니다. Ggogit에 가입해주셔서 진심으로 감사드립니다.</p>" +
                "<br/>" +
                "<p>아래 링크를 클릭하여 회원가입을 완료해주세요.</p>" +
                "<br/>" +
                "<a href='http://localhost:8080/member/join-input'>회원가입 완료하기</a>" +
                "<br/>" +
                "<p>감사합니다.</p>" +
                "<br/>" +
                "<p>Ggogit 드림</p>", true);

        javaMailSender.send(message);
    }
}