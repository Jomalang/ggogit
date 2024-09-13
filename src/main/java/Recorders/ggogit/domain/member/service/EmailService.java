package Recorders.ggogit.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void sendEmail(String email) {

        String setForm = "rhkddlr98@naver.com"; //보내는 사람
        String toEmail = email; //받는 사람
        String title = "ggogit 회원가입 페이지"; //제목

    }
}
