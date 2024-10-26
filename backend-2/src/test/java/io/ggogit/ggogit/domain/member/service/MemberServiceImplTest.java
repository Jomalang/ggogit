package io.ggogit.ggogit.domain.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secretKey;

    @Test
    @DisplayName("비밀번호 적용 테스트")
    void join() {
        // given

        // when
        System.out.println("password = " + passwordEncoder.encode(secretKey + "mypassword"));

        // then
    }
}